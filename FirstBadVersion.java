// 变种：不知道一共多少版本
/*
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
*/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low / 2 + high / 2;         // (low + high) / 2 may cause Time Limit Exceeded (TLE), because of overflow.
            if (!isBadVersion(mid)) {
                low = mid + 1;
            }
            if (isBadVersion(mid)) {
                high = mid;
            }
        }
        if (low == high)
            return low;
        return 0;
    }
}


// 变种是如果不知道一共有多少版本的情况下应该怎么找。 Unknow numbr of version
public int badVersion {
    int prev = 0;
    int cur = 0;
    while (isBadVersioin(cur)) {
        prev = cur;
        cur = cur * cur;
    }
    int start = prev;
    int end = cur;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (isBadVersioin(mid)) {
            end = mid;
        }
        else {
            start = mid;
        }
    }
    if (isBadVersioin(start)) {
        return start;
    }
    return end;
}
