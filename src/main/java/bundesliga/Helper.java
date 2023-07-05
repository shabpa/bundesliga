package bundesliga;

public class Helper {

    /**
     * In keys for HashMaps, spaces and also upper and lower case 
     * letters should be irrelevant. This method generates a key 
     * from the given name.
     * 
     * @param name The name from which the key should be created
     * @return A key derived from the given name
     * @throws IllegalArgumentException if name is null or empty
     */
    public static String getKeyByName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("name must not be null or empty");
        }
        
        name = name.trim().replaceAll(" ","").toUpperCase();
        return name;
    }


    /**
     * Fills up a string with spaces up to the given length
     * 
     * @param s the String to be filled
     * @param length the length of the new string
     * @return the filled String
     * @throws IllegalArgumentException if s is null
     */
    public static String fill(String s, int length) {
        if(s==null){
            throw new IllegalArgumentException("s must not be null");
        }
       
        if (s.length() >= length) 
            return s;
        
        StringBuilder sb = new StringBuilder(s);

        while (sb.length() < length) {
            sb.append(" ");
        }

        return sb.toString();
    }
    
}
