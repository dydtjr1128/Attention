package kr.ac.hifly.attention.voiceCore;

import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;

import kr.ac.hifly.attention.data.Call;
import kr.ac.hifly.attention.value.Values;

public class Call_Thread extends Thread{
    private String connect_IP;
    private InetSocketAddress inetSocketAddress;
    private DatagramSocket datagramSocket;
    private AudioRecord audioRecord;
    private byte audioBuffer[];
    private int BUFFER_SIZE;
    int count=0;
    public Call_Thread(String userIP){
        this.connect_IP = userIP;
        Call_Receive_Thread call_receive_thread = new Call_Receive_Thread(userIP);
        call_receive_thread.start();
    }
    public void run(){
        try {
         /*   socket = new Socket(Values.SERVER_IP, Values.SERVER_PORT);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            //dos.writeUTF("sendName " + UUID.randomUUID().toString().replace("-",""));
            //dos.writeUTF("sendName " + user.getUuid());
            dos.writeUTF("calling " + "5b2fecb7ab1149288fd18618220a2ed3");//누른사람의 uuid를 전송
            String message = dis.readUTF();
            if(!message.equals(null))
                connect_IP = message;
            while(true){
                String startMessage = dis.readUTF();
                if(startMessage.equals("start"))
                    break;
            }*/
            inetSocketAddress = new InetSocketAddress(connect_IP,Values.SERVER_PORT);
            datagramSocket = new DatagramSocket();
            initAudioSetting();
            audioRecord.startRecording();
        }catch (Exception e){
            e.getStackTrace();
        }
        Log.i(Values.TAG, "sending");
        while(true) {
            try {
                int read = audioRecord.read(audioBuffer, 0, audioBuffer.length);
                if(count==500) {
                    Log.i(Values.TAG, "sending");
                    count=0;
                }

                datagramSocket.send(new DatagramPacket(audioBuffer, 0, read, inetSocketAddress));
            } catch (Exception e) {
                Log.i(Values.TAG, "Voice Error");
                e.getStackTrace();
                return;
            }
        }
    }


    public void initAudioSetting() {
        BUFFER_SIZE = AudioRecord.getMinBufferSize(Values.RECORDING_RATE, Values.AUDIO_CHANNEL, Values.AUDIO_FORMAT);
        audioBuffer = new byte[BUFFER_SIZE];

        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, Values.RECORDING_RATE, Values.AUDIO_CHANNEL, Values.AUDIO_FORMAT, BUFFER_SIZE);
    }

}
