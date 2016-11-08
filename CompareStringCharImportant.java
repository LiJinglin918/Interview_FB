/* 自然string comparator。不知道的搜下。就是string 比较的时候考虑里面数字的大小，比如 abc9 < abc123 abc > ab9  因为char比digit重要。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=179527&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
*/








/**
 * 题目要求是，char比digit重要。数儿要按照几位数比较。比如abc9 < abc123 (因为 9 < 123)
 * 1. use two pointer to compare
 * 2. if both are char and equals, both are number, 
 * 3. Finally not equals 
 * 4. If i and j reach the end (check all the char already), return 0
 */
 
class CompareString implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < str1.length() && index2 < str2.length()) {
            char letter1 = str1.charAt(index1);
            char letter2 = str2.charAt(index2);
			
			// both are char and equals
            if (letter1 == letter2 &&  !Character.isDigit(letter2)) { 
                index1++;
                index2++;
            }
			
			//both are number
            else if (Character.isDigit(letter1) && Character.isDigit(letter2)){ 
                int number1 = 0;
                int number2 = 0;
				
				// calculate the number
                while (index1 < str1.length() && Character.isDigit(str1.charAt(index1))) {
                    number1 = number1 * 10 + str1.charAt(index1) - '0';
                    index1++;
                }
                while (index2 < str2.length() && Character.isDigit(str2.charAt(index2))) {
                    number2 = number2 * 10 + str2.charAt(index2) - '0';
                    index2++;
                }
                if (number1 > number2) {
                    return 1;
                }
                else if (number1 < number2) {
                    return -1;
                }
            }
			
			// not equals or one is number
            else { 
                if (Character.isDigit(letter2)) {
                    return 1;
                }
                else if (Character.isDigit(letter1)) {
                    return -1;
                }
                if (letter1 > letter2) {
                    return 1;
                }
                return -1;
            }
        }
		
		//check all the char already
        if (index1 == str1.length() && index2 == str2.length()) { 
            return 0;
        }
        else if (index1 < str1.length()) {
            return 1;
        }
        return -1;
    }
}
