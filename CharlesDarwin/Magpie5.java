package CharlesDarwin;

import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 *         Uses advanced search for keywords 
 *</li><li>
 *         Will transform statements as well as react to keywords
 *</li></ul>
 * This version uses an array to hold the default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie5
{
    /**
     * Get a default greeting     
     * @return a greeting
     */    
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }

        else if (findKeyword(statement, "no") >= 0)
        {
            response = "Why so negative?";
        }
        else if (findKeyword(statement, "What's your name") >=0
            || findKeyword(statement, "What is your name?") >= 0)
        {
            response = "I am known as Charles Darwin";
        }
        else if (findKeyword(statement, "What are you famous for?") >=0
            || findKeyword(statement, "What are you known for?") >= 0)
        {
            response = "I am known for my Natural Selection theory and more importantly, Social Darwinism.";
        }
        else if (findKeyword(statement, "Who were you married to?") >=0
            || findKeyword(statement, "Who are you married to?") >= 0
            || findKeyword(statement, "Were you married?") >= 0)
        {
            response = "My beloved first cousin Emma Darwin.";
        }
        else if (findKeyword(statement, "Do you have any children?") >=0
            || findKeyword(statement, "Did you have any children?") >= 0
            || findKeyword(statement, "Do you have children?") >= 0
            || findKeyword(statement, "Did you have children?") >= 0)
        {
            response = "Yes, I have 10 genetically superior children than those plebians.";
        }
        else if (findKeyword(statement, "What are their names?") >=0)
        {
            response = "Ah yes my superior children Francis Darwin, Anne Darwin, Charles Waring Darwin, George Darwin, Mary Eleanor Darwin, Leonard Darwin, Horace Darwin, Henrietta Litchfield, Elizabeth Darwin, and William Erasmus Darwin.";
        }
        else if (findKeyword(statement, "Where were you born?") >=0)
        {
            response = "I was born in the Mount House, Shrewsbury, England.";
        }
        else if (findKeyword(statement, "Where do you live?") >=0)
        {
            response = "I currently live 6ft below the ground at Westminster Abbey, London.";
        }
        else if (findKeyword(statement, "What do you think of Europeans?") >=0)
        {
            response = "We are genetically better and therefore most fit to rule over lesser beings.";
        }
        else if (findKeyword(statement, "What is your view on imperialism in India?") >=0)
        {
            response = "Deserved";
        }
        else if (findKeyword(statement, "Why do you believe you and people like you are superior?") >=0)
        {
            response = "We genetically are.";
        }
        else if (findKeyword(statement, "What do you think about Queen Elizabeth's death?") >=0)
        {
            response = "An unfortunate loss. Make sure her lesser subordinates mourn her for years to come.";
        }
        else if (findKeyword(statement, "Why are you so old and bald?") >=0)
        {
            response = "Why are you talking to me you lesser being. Come back when your genetics are on par with mine.";
        }
        else if (findKeyword(statement, "apple") >=0)
        {
            response = "Have you heard of Sir Issac Newton? He was a great man who discovered the amazing concept known as gravity. Quite befitting of our superior genetics.";
        }
        else if (findKeyword(statement, "have you heard of the witch of Patrick Henry") >=0)
        {
            response = "Ah yes, Wendy Lange was it? I don't associate her with our wonderful kind. She belongs with the rest of those lesser beings";
        }
        else if (findKeyword(statement, "What is the estimated value of pi") >=0)
        {
            response = "3.1415926535 8979323846 2643383279 5028841971 6939937510 5820974944 5923078164 0628620899 8628034825 3421170679";
        }
        else if (findKeyword(statement, "How old are you?") >=0)
        {
            response = "I am 73 years old";
        }
        else if (findKeyword(statement, "What did you do?") >=0)
        {
            response = "I created the theories known as Social Darwinism and Natural Selection";
        }
        else if (findKeyword(statement, "evolution") >=0)
        {
            response = "People like me have evolved and are genetically superior those those behind the curve.";
        }
        else if (findKeyword(statement, "Hi") >=0)
        {
            response = "Hello";
        }
        else if (findKeyword(statement, "mother") >= 0
                || findKeyword(statement, "father") >= 0
                || findKeyword(statement, "sister") >= 0
                || findKeyword(statement, "brother") >= 0)
        {
            response = "Tell me more about your family.";
        }

        // Responses which require transformations
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        }
        //  Part of student solution
        else if (findKeyword(statement, "I want", 0) >= 0)
        {
            response = transformIWantStatement(statement);
        }

        else
        {

            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "you", 0);

            if (psn >= 0
                    && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement);
            }
            else
            {
                //  Part of student solution
                // Look for a two word (I <something> you)
                // pattern
                psn = findKeyword(statement, "i", 0);

                if (psn >= 0
                        && findKeyword(statement, "you", psn) >= 0)
                {
                    response = transformIYouStatement(statement);
                }
                else
                {
                    response = getRandomResponse();
                }
            }
        }
        return response;
    }
    
    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }

    
    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    private String transformIWantStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }
    
    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    private String transformYouMeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }
    
    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    private String transformIYouStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfI = findKeyword (statement, "I", 0);
        int psnOfYou = findKeyword (statement, "you", psnOfI);
        
        String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
        return "Why do you " + restOfStatement + " me?";
    }
    

    
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        //  The only change to incorporate the startPos is in the line below
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
        
        //  Refinement--make sure the goal isn't part of a word 
        while (psn >= 0) 
        {
            //  Find the string of length 1 before and after the word
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
            
            //  If before and after aren't letters, we've found the word
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
                    && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }
            
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
            
        }
        
        return -1;
    }
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }
    


    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse ()
    {
        Random r = new Random ();
        return randomResponses [r.nextInt(randomResponses.length)];
    }
    
    private String [] randomResponses = {"Interesting, tell me more",
            "Hmmm.",
            "Do you really think so?",
            "Haha",
            "e",
            "your mother",
            "Want a break from the ads?",
            "You don't say."
    };
    
}
