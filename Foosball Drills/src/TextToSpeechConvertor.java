/**
 * Created by naveen.veeravalli on 2/14/18.
 */

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeechConvertor {

    // Some available voices are (kevin, kevin16, alan)
    private static final String VOICE_NAME_KEVIN = "kevin16";
    //    private static final String VOICE_NAME_KEVIN = "alan"; //uncomment if setting the System.setProperty to AlanVoiceDirectory in line 17
    private final Voice voice;

    public TextToSpeechConvertor() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICE_NAME_KEVIN);
        voice.allocate();
    }

    public void speak(String inputText) {

        if(inputText != null && !inputText.isEmpty()) {

            voice.speak(inputText);
        }
    }

}
