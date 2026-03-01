        }

        high += k; // max possible increase

        int ans = low;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canMake(arr, n, k, w, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canMake(int[] arr, int n, int k, int w, int target) {
        long[] diff = new long[n + 1];
        long usedWater = 0;
        long currAdd = 0;

        for (int i = 0; i < n; i++) {
            currAdd += diff[i];
            long currentHeight = arr[i] + currAdd;

            if (currentHeight < target) {
                long need = target - currentHeight;
                usedWater += need;

                if (usedWater > k) return false;

                currAdd += need;
                if (i + w < diff.length) {
                    diff[i + w] -= need;
                }
            }
        }
        return true;
    }
}
