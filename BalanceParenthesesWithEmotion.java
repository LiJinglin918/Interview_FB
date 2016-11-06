// 97. Balance Parenthese With Emotion
/*
input could include ":(" frown or ":)" smileys
check if the input is parenthese balance
*/

class CheckBalanced {
    public boolean check(String message) {
        int closeParenth = 0;
        int openParenth = 0;
        int smileys = 0;
        int frowns = 0;
        boolean isPotentialEmotion = false;
        for (char letter : message.toCharArray()) {
            if (letter == '(') {
                if (isPotentialEmotion) {
                    frowns++;
                }
                openParenth++;
            }
            else if (letter == ')') {
                if (isPotentialEmotion) {
                    smileys++;
                }
                closeParenth++;
            }
            if (closeParenth > openParenth) {
                if (closeParenth - smileys > openParenth) {
                    return false;
                }
                closeParenth = openParenth;
                smileys = smileys - (closeParenth - openParenth);
            }
            if (letter == ':') {
                isPotentialEmotion = true;
            }
            else {
                isPotentialEmotion = false;
            }
        }
        if (openParenth - frowns > closeParenth) {
            return false;
        }
        return true;
    }
}
