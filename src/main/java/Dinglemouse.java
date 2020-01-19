public class Dinglemouse {
    private static final int NO_DEAF_RATS = 0;
    private static final String HAMLET = "P";
    private static final String RAT_FACING_RIGHT = "~0";
    private static final String RAT_FACING_LEFT = "0~";

    public static int countDeafRats(String rats) {
        if (!thereAreAny(rats)) return NO_DEAF_RATS;
        rats = normalizeSpaces(rats);
        if(isHamelinAtTheFront(rats)){
            return countDeafRats(rats, RAT_FACING_RIGHT);
        }else if (isHamelinAtTheEnd(rats)){
            return countDeafRats(rats, RAT_FACING_LEFT);
        }else{
            int hamelinPosition = searchHamelin(rats);
            return countDeafRats(rats, hamelinPosition);
        }
    }

    private static int countDeafRats(String rats, int hamelinPosition) {
        return countDeafRats(rats.substring(hamelinPosition), RAT_FACING_RIGHT)
                + countDeafRats(rats.substring(0, hamelinPosition+1), RAT_FACING_LEFT);
    }

    private static boolean isHamelinAtTheEnd(String rats) {
        return searchHamelin(rats) == rats.length()-1;
    }

    private static String normalizeSpaces(String rats) {
        return rats.replace(" ", "");
    }

    private static boolean isHamelinAtTheFront(String rats) {
        return searchHamelin(rats) == 0;
    }

    private static int searchHamelin(String rats) {
        return rats.indexOf(HAMLET);
    }

    private static int countDeafRats(String rats, String deafRat){
        int deafRats = 0;
        while (hamletIsNotAlone(rats)){
            String rat = getFirstRat(rats);
            if (ratIsNotPayingAttention(rat, deafRat)) deafRats++;
            rats = rats.replaceFirst(rat, "");
        }
        return deafRats;
    }

    private static boolean ratIsNotPayingAttention(String rat, String deafRat) {
        return rat.equals(deafRat);
    }

    private static String getFirstRat(String rats) {
        return (isHamelinAtTheFront(rats))? rats.substring(1, 3) : rats.substring(0, 2);
    }

    private static boolean hamletIsNotAlone(String rats) {
        return !rats.equals(HAMLET);
    }

    private static boolean thereAreAny(String rats) {
        return rats.contains(RAT_FACING_LEFT) || rats.contains(RAT_FACING_RIGHT);
    }
}
