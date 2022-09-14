/*
Write a Java program that helps validate email addresses. For the purpose of this question, we will
focus on checking whether a given string represents a syntactically correct email address.
A valid email address consists of a prefix, an ‘@’ symbol, and an email domain. Both the prefix and the domain must be
written in acceptable formats. For instance, in the address john.smith@mail.com, “john.smith” is the prefix, and
“mail.com” is the domain.
*/

import java.util.Scanner;

public class EmailValidation {

    public static boolean isAlphanumeric(char aC){
        //return true if c is Alphanumeric
        return(Character.isLetterOrDigit(aC));
    }

    public static boolean isValidPrefixChar(char pC){
        //return true if c is Alphanumeric, an underscore, a period, or a dash
        return(isAlphanumeric(pC) || pC == '_' || pC == '.' || pC == '-');
    }

    public static boolean isValidDomainChar(char dC){
        //return true if c is Alphanumeric, a period, or a dash
        return(isAlphanumeric(dC) || dC == '.' || dC == '-');
    }

    public static boolean exactlyOneAt(String s){
        int x = s.indexOf('@'); //search s for the first '@'
        //if no '@' is found, return false
        if(x==-1){
            return false;
        }
        //if an '@' is found, search s again, starting from the character next to the found '@'
        //return true if no more '@'s are found
        else{
            x++;
            int x2 = s.indexOf('@',x);
            return x2 == -1;
        }
    }

    public static String getPrefix(String s){
        int x = s.indexOf('@');//search s for the first '@'
        return s.substring(0,x);//return a substring, up to x
    }

    public static String getDomain(String s){
        int x = s.indexOf('@');//search s for the first '@'
        return s.substring(x+1);//return a substring, from x onwards (excluding @)
    }

    public static boolean isValidPrefix(String pPrefix){

        /*"It contains at least one character.
        The first character must be alphanumeric."*/
        char c = pPrefix.charAt(0);

        if(!isAlphanumeric(c)){
            return false;
        }

        //loop will run through the prefix and verify each char
        for(int i = 0; i < pPrefix.length(); i++) {

            c = pPrefix.charAt(i);

            //special statement for the final character of the prefix, since d would otherwise be out of range
            if(i == pPrefix.length()-1){
                //return true if the final character is alphanumeric and is not a special character
                return isAlphanumeric(c) && ((c != '_') && (c != '.') && (c != '-'));
            }
            /*
             "- It contains only alphanumeric characters, underscores (‘_’), periods (‘.’), and dashes (‘-’).
             - An underscore, a period, or a dash must always be followed by one or more alphanumeric characters."

            (!isValidPrefixChar(c)): calls ValidPrefixChar method to verify whether c is valid
            if c is not valid, the method will return false and the expression will return true
            then the if statement will execute and the overall method (isValidPrefix) will return false

            ((c == '_') || (c == '.') || (c == '-')): verifies whether (c) is one of the special characters
            !isAlphanumeric(d): calls isAlphanumeric method to verify if (d), the character after (c), is alphanumeric
            if both statements above return true (i.e. (c) is a special character and (d) is not alphanumeric)
            then the if statement will execute and the overall method (isValidPrefix) will return false
            */
            char d = pPrefix.charAt(i+1);
            if ((!isValidPrefixChar(c)) || (((c == '_') || (c == '.') || (c == '-')) && !isAlphanumeric(d))){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDomain(String pDomain){

        //"It is made up of two portions separated by a period."
        int x = pDomain.lastIndexOf('.');//search pDomain for the last '.'

        //lastIndexOf will return -1 if it does not find a '.' so the method will return false
        if (x == -1) return false;

        String firstPortion = pDomain.substring(0,x);//find a substring from 0 to x
        String secondPortion = pDomain.substring(x+1);//find a substring, from x onwards

        //"The first portion contains at least one character."
        if(firstPortion.length()<1) return false;
        //"The second portion contains at least two characters."
        if(secondPortion.length()<2) return false;

        //loop will run through the first portion and verify each char
        for(int i = 0; i < firstPortion.length(); i++) {

            char c = firstPortion.charAt(i);

            //special statement for the final character of the first portion, since d would otherwise be out of range
            if (i == firstPortion.length() - 1) {
                //return false if the final character is not alphanumeric
                if (isAlphanumeric(c)) break;
                else return false;
            }
            /*
             "- The first portion contains only alphanumeric characters, periods (‘.’), and dashes (‘-’).
             - A period or a dash must always be followed by one or more alphanumeric characters."

            (!isValidDomainChar(c)): calls ValidDomainChar method to verify whether c is valid
            if c is not valid, the method will return false and the expression will return true
            then the if statement will execute and the overall method (isValidDomain) will return false

            ((c == '.') || (c == '-')): verifies whether (c) is one of the special characters
            !isAlphanumeric(d): calls isAlphanumeric method to verify if (d), the character after (c), is alphanumeric
            if both statements above return true (i.e. (c) is a special character and (d) is not alphanumeric)
            then the if statement will execute and the overall method (isValidDomain) will return false
            */
            char d = firstPortion.charAt(i + 1);
            if ((!isValidDomainChar(c)) || (((c == '.') || (c == '-')) && !isAlphanumeric(d))) return false;
        }

        //loop will run through the second portion and verify each char
        for(int j = 0; j < secondPortion.length(); j++){

            char e = secondPortion.charAt(j);

            //"The second portion contains only letters of the alphabet."
            //return false if any character is not a letter
            if(!Character.isLetter(e)) return false;
        }
        return true;
    }

    public static boolean isValidEmail(String pEmail){

        //if more than 1 @ return false
        boolean a = exactlyOneAt(pEmail);
        if (!a) return false;

        String prefix = getPrefix(pEmail);
        String domain = getDomain(pEmail);

        //if invalid prefix return false
        boolean b = isValidPrefix(prefix);
        if (!b) return false;

        //if invalid domain return false
        return isValidDomain(domain);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("\nPlease enter your email address:\n");//ask for user input
        String userEmail = s.nextLine();

        boolean a = isValidEmail(userEmail);//check if email is valid

        if (a) System.out.println("\nAccepted - thank you");
        else System.out.println("\nInvalid email");
    }
}
