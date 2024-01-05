package backend.env;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NftSlugList {
    public static final String[] slugs = {  "bored-ape-yacht-club" ,
         "azuki" ,
         "proof-moonbirds" ,
         "pudgy-penguins" ,
         "bored-ape-kennel-club" ,
         "meebits" ,
         "degods" ,
         "otherdeed" ,
         "mutant-ape-yacht-club" ,
         "cryptopunks"};

    public static final String[] nftNames = {  "Bored Ape Yacht Club" ,
            "Azuki" ,
            "Proof Moonbirds" ,
            "Pudgy Penguins" ,
            "Bored Ape Kennel Club" ,
            "Meebits" ,
            "Degods" ,
            "Otherdeed" ,
            "Mutant Ape Yacht Club" ,
            "Cryptopunks"};
    public static List<String> slugList = new ArrayList<>(Arrays.asList(slugs));
}
