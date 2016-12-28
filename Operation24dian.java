import java.util.*;
public class Operation24dian {
	public static void main(String[] args) {
		System.out.println(new Operation24dian().addPlusAndMinus("123456789", 100));
	}
	public List<String> addPlusAndMinus(String num, int target){
        if(num==null) throw new IllegalArgumentException();

        List<String> res = new ArrayList<String>();
        backtrack(res, num, 0, 0, target, "");
        return res;
    }

    private void backtrack(List<String> res, String s, int pos, int eval, int target, String cur){
        if(pos==s.length()){
            // check condition and make sure string cur is not empty
            if(eval==target && cur.length() > 0) 
            	res.add(cur + " = " + target);
            else 
            	return;
        }
        else {
            for(int i=pos+1; i<=s.length(); i++){

                // check illegal substring in forms like "02"
                if(i>pos+1 && s.charAt(pos)=='0') 
                	break;

                int num = Integer.parseInt(s.substring(pos, i));

                // the first number
                if(cur.length()==0) 
                	backtrack(res, s, i, num, target, num+"");
                else{
                    backtrack(res, s, i, eval+num, target, cur + " + " + num);
                    backtrack(res, s, i, eval-num, target, cur + " - " + num);
                }
            }
        }
    }
}
