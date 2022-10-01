package Clock;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Test03 {
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
		try {
			Clip bgm = AudioSystem.getClip();
			InputStream is = Test03.class.getClassLoader().getResourceAsStream("sound/horizons.wav");
//			InputStream is = this.getClass().getClassLoader().getResourceAsStream("sound/horizons.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(is);
			bgm.open(ais);
			bgm.start();
			
			System.out.println("主人、闹钟响了");
			while(true) {
			}
		} catch (LineUnavailableException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}