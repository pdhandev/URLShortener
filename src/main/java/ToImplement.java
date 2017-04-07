import java.util.HashMap;
/*
        Mapped 62 values (a-zA-Z0-9) where the array "valuesForShorteningURL" stores all possible
        variables used for mapping index-value (index zero offset, not to be used anytime)
        1-a, 2-b,...,26->z , 27->A, ...,52->Z,53->0, 54->1....... 62->9

        HashMap "mapToGetOriginalURL" for fast lookUp of already stored shortenedURI
        key: shortURI
        value: longURL

        "ShortenedURICount" to keep track of the total number of URLs shortened so far

 */
public class ToImplement {

    private HashMap<String, String> mapToGetOriginalURL = new HashMap<>();
    private String[] valuesForShorteningURL = {"0","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
                                                "p","q","r","s","t","u","v","w","x","y","z","A","B","C","D",
                                                "E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
                                                "T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7",
                                                "8","9"};

    private int ShortenedURICount = 0;


    /*
        This method returns longURL from HashMap where it was stored while setting shortURI
     */
    public String get(String shortURI) {
        String longURL =  mapToGetOriginalURL.get(shortURI);
        return longURL;
    }

    /*
        This method returns a shortURI for longURL that is created with helper method getShortURI()

        I update a HashMap whenever a new URL entry is Shortened
     */
    public String set(String longURL) {
        String shortURI = getShortURI();
        mapToGetOriginalURL.put(shortURI, longURL);
        return shortURI;
    }

    /*
        This method sets a shortURI for longURL

        Incrementing "ShortenedURICount" every time the set method is called to create a new ShortURI
        Using this "ShortenedURICount" generating digits which helps to map to the values in array
        "valuesForShorteningURL"

        Using a StringBuilder object to create ShortURI and the reverse operation will provide the final
        ShortURI

        ex. 63rd URL should be mapped to aa as 1-62 URLs have consumed all single sized ShortURL : a to 9
        So using the logic below digits generated are 11-->aa and then reversing it we get aa

        ex. 64th gives digits 21-->ba and then reversing it we get ab
    */
    private String getShortURI(){
        ShortenedURICount++;

        int tempCount = ShortenedURICount;
        int rem;
        StringBuilder str = new StringBuilder();

        if(tempCount%62==0){
            str.append(valuesForShorteningURL[62]);
            tempCount /= 62;
            if(tempCount>1){
                str.append(valuesForShorteningURL[tempCount-1]);
            }
        }
        else{
            while(tempCount>0) {
                rem = tempCount % 62;
                str.append(valuesForShorteningURL[rem]);
                tempCount /= 62;
            }
        }

        String encodedShortURI = new String(str.reverse());

        return encodedShortURI;
    }

}
