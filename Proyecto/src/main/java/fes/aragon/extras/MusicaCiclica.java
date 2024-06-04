/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.extras;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Administrator
 */
public class MusicaCiclica {

	private BufferedInputStream buffer = null;
	private FileInputStream archivo;
	private String nombreArchivo;
	private AudioInputStream audio;
	private Clip clip;
    private boolean musicaIsRunning = true;
	
	public MusicaCiclica(String archivo) throws UnsupportedAudioFileException, IOException {
		this.nombreArchivo = archivo;
		try {
			this.archivo = new FileInputStream(
					this.getClass().getResource("/fes/aragon/musica/" + nombreArchivo + ".wav").toURI().getPath());
			buffer = new BufferedInputStream(this.archivo);
			audio = AudioSystem.getAudioInputStream(buffer);
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void reproducir() {
		if(musicaIsRunning) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}else if(!musicaIsRunning) {
			//clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.stop();
		}

	}
	
	public void detener() {
		musicaIsRunning = false;
		reproducir();
	}
	public void continuar() {
		musicaIsRunning = true;
		reproducir();
	}
}
