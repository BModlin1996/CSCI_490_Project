/**
 * Card Verification
 * This class verifies that the credit card number is real
 *
 * @author Bailey Modlin
 * @version 1.0
 * @since 9/4/2018
 */
package com.games.baileymodlin.csci_490_project;

public class CardVerify {

    private static CardVerify verify = new CardVerify();

    private CardVerify() {

    }

    public static CardVerify getInstance(){
        return verify;
    }

    /**
     * Verify
     *
     * @param num
     * @return
     */
    public boolean verifyCardNum(String num) {
        if (luhn(num)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @param num
     * @return
     */
    private boolean luhn(String num) {
        int sum = 0;
        int digits = num.length();
        int parity = (digits - 1)%2;
        int i;
        char[] numChar = num.toCharArray();
        int digit;

        if (num.length() == 16) {
            for (i = digits; i > 0; i--) {

                digit = numChar[i - 1];

                if(parity == i%2){
                    digit *= 2;
                }
                sum += digit/10;
                sum += digit%10;
            }

            if(sum%10 == 0){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean verifyName(String name){
        if(nameVerify(name)){
             return true;
        } else  {
            return false;
        }
    }

    private boolean nameVerify(String name){
        char charArray[] = name.toCharArray();
        for(int i = 0; i < name.length(); i++){
            if((charArray[i] < 'A' || charArray[i] > 'Z') && (charArray[i] < 'a' || charArray[i] > 'z')){
              return false;
            }
        }
        return true;
    }

    public boolean verifyDate(String date){
        if(dateVerify(date)){
            return true;
        } else {
            return false;
        }
    }

    private boolean dateVerify(String date){
        return true;
    }
}
