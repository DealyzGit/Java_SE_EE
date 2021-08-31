package org.fangsoft.util;

public class regx_urlpage {
    public static void main(String[] args) {
        String s="commitTest?2_8=on&2_9=on&2_10=on&2_11=on&0_0=on&0_1=on&0_2=on&0_3=on&1_4=on&1_5=on&1_6=on&1_7=on";
        String[] n_s =s.split("&");
        for (String c:n_s
             ) {
            System.out.println(c);
        }
    }
}
