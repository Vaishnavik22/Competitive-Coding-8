/*
Problem: https://leetcode.com/problems/minimum-window-substring/
*/

// TC: O(n + m)
// SC: O(m)
class Solution {
    HashMap<Character, Integer> freqMap = null;
    
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || t.length() > s.length()) {
            return "";
        }
        
        freqMap = new HashMap<>();
        int matches = 0;
        int minLen = s.length() + 1;
        int startIndex = 0;
        int windowStart = 0, windowEnd = 0;
        
        for (char ch : t.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        
        for (windowEnd = 0; windowEnd < s.length(); ++windowEnd) {
            char ch = s.charAt(windowEnd);
            if (freqMap.containsKey(ch)) {
                freqMap.put(ch, freqMap.get(ch) - 1);
                if (freqMap.get(ch) == 0) ++matches;
            }
                
            while (matches == freqMap.size()) {
                if (windowEnd - windowStart + 1 < minLen) {
                    minLen = windowEnd - windowStart + 1;
                    startIndex = windowStart;
                }
                char windowStartCh = s.charAt(windowStart);
                if (freqMap.containsKey(windowStartCh)) {
                    freqMap.put(windowStartCh, freqMap.get(windowStartCh) + 1);
                    if (freqMap.get(windowStartCh) == 1) --matches;
                }
                // shrink window
                ++windowStart;
            }
        }
        
        return minLen == s.length() + 1 ? "" : s.substring(startIndex, startIndex + minLen);
    }
}