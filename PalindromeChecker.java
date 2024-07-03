
public class PalindromeChecker {

  public boolean isPalindrome(String aText) {

    if (aText.length() <= 1){
        return true;
    }

    char firstChar = Character.toLowerCase(aText.charAt(0));
    char lastChar =  Character.toLowerCase(aText.charAt(aText.length() - 1));

    // You can add this line to help show the process
    //System.out.println(aText+" ->"+firstChar + " ->"+lastChar);

    if (Character.isLetter(firstChar) && Character.isLetter(lastChar)){
        if (firstChar == lastChar){
            String substring = aText.substring(1, aText.length()-1);
            return this.isPalindrome(substring);
        }
        else{
            return false;
        }
    }
    else if (!Character.isLetter(lastChar)){ // remove last character
        String substring = aText.substring(0, aText.length()-1);
        return this.isPalindrome(substring);
    }
    else{ // remove first character
        String substring = aText.substring(1, aText.length());
        return this.isPalindrome(substring);
    }

  }
}
