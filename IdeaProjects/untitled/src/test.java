public class test {
    class test
    {
        int test_a, test_b;
        test(int a, int b)
        {
            this.test_a = a;
            this.test_b = b;
        }
        public static void main (String args[])
        {
            test Test = new test(3,5);
            System.out.println(this.Test_a+" "+this.Test_b);
        }
    }
    static boolean isPalindrome(int num,int temp, int reverseNum){

            if(temp == 0)
                return num == reverseNum;

            int rem = temp%10;

            return isPalindrome(num,temp/10,reverseNum*10+rem);

}
/*static void modify(String text, String pattern,String newPattern){

        boolean matches = false;
            char[] ch = new char[text.length()];
            for(int i = 0; i < ch.length; i++){
                if(ch[i] == pattern.charAt(0)){
                    int temp = i,newPatternIndex = 0;
                    for(; i < temp+pattern.length(); i++){
                        if()
                    }
                }
            }

        }*/
}
