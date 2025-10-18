package util;
public class CodecSigne {
    public static int encoderZigZag(int v) {
        return (v << 1) ^ (v >> 31);
    }

    public static int decoderZigZag(int u) {
        return (u >>> 1) ^ -(u & 1);
    }
}
