import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by naveen.veeravalli on 2/14/18.
 */
public class PractiseShotGenerator {

    public static final int MAX_TIME_3_BAR = 10; //in secs
    public static final int MAX_TIME_2_BAR = 10; //in secs
    public static final int MAX_TIME_5_BAR = 8; //in secs

    public static final int RESET_TIME = 4; //in secs
    public static final int MAX_SHOTS = 60;

    public static final List<String> THREE_BAR_SHOTS = Arrays.asList("Pull",
                                                                    "Straight",
                                                                    "Push");
    public static final List<String> TWO_BAR_SHOTS = Arrays.asList("Short","Middle","Long");
    public static final List<String> FIVE_BAR_SHOTS = Arrays.asList("Lane","Wall","Quick Wall", "Quick off the wall");

    public static void main(String[] args) {

        try {
            announce3BarShots();
//            announce2BarShots();
//            announce5BarShots();
        } catch(Exception e) {
            //do nothing
        }
    }

    public static void announce3BarShots() throws Exception {
        announceRandomItem(THREE_BAR_SHOTS,MAX_TIME_3_BAR, MAX_SHOTS);
    }

    public static void announce5BarShots() throws Exception {
        announceRandomItem(FIVE_BAR_SHOTS,MAX_TIME_5_BAR, MAX_SHOTS);
    }

    public static void announce2BarShots() throws Exception {
        announceRandomItem(TWO_BAR_SHOTS,MAX_TIME_2_BAR, MAX_SHOTS);
    }

    public static void announceRandomItem(List<String> list, int maxTimeIntervalBetweenShots, int maxRandomItems) throws Exception {
        TextToSpeechConvertor textToSpeechConvertor = new TextToSpeechConvertor();
        HashMap<String, Integer> totalCountsPerShot = new HashMap<>(maxRandomItems);
        for(int i = 0; i < maxRandomItems; i++){

            //RESET TIME
             TimeUnit.SECONDS.sleep(RESET_TIME);

            int intervalBetweenShots = 1+ getRandomNumber(maxTimeIntervalBetweenShots); //1 is added to have at least 1 sec
            TimeUnit.SECONDS.sleep(intervalBetweenShots);
            String randomShot = announceRandomItem(list);
            System.out.println(randomShot + " " + intervalBetweenShots + " seconds");
            textToSpeechConvertor.speak(randomShot);
            int count = 0;
            if(totalCountsPerShot.get(randomShot) != null) {
                count = totalCountsPerShot.get(randomShot) + 1;
            } else {
                count = 1;
            }
            totalCountsPerShot.put(randomShot, count);
        }

        TimeUnit.SECONDS.sleep(RESET_TIME);
        textToSpeechConvertor.speak("Well Done");
        System.out.println(totalCountsPerShot.toString());
    }

    public static String announceRandomItem(List<String> list) {
        int index = getRandomNumber(list.size());
        return list.get(index);
    }

    /**
     * returns random number between 0 and maxNumber-1 (inlusive)
     */
    public static int getRandomNumber(int maxNumber) {
        return ThreadLocalRandom.current().nextInt(maxNumber);
    }
}
