package com.rainz;

/*
 * Given a file and assume that you can only read the file using a given method read4, implement a method read to read n characters. Your method read may be called multiple times.
 */
public class ReadNCharactersGivenRead4II {
    public static void test(String args[]) {
        char[] input1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
        ReadNCharactersGivenRead4II rn = new ReadNCharactersGivenRead4II(input1);
        int len;
        do {
            char[] buf = new char[7];
            len = rn.read(buf, buf.length);
            System.out.println(len);
            Main.printArray(buf);
        } while (len > 0);

        char[] input2 = {'a', 'b', 'c'};
        ReadNCharactersGivenRead4II rn2 = new ReadNCharactersGivenRead4II(input2);
        char[] buf2 = new char[32];
        System.out.println(rn2.read(buf2, 1));
        Main.printArray(buf2);
        System.out.println(rn2.read(buf2, 2));
        Main.printArray(buf2);
        System.out.println(rn2.read(buf2, 1));
        Main.printArray(buf2);
    }

    private char[] _data = null;
    private int _offset4 = 0;
    public ReadNCharactersGivenRead4II(char[] data) {
        _data = data;
    }

    private int read4(char[] buf) {
        int i = 0;
        while (i < 4) {
            if (_offset4 < _data.length)
                buf[i++] = _data[_offset4++];
            else
                break;
        }
        return i;
    }

    private char[] _buf4 = new char[4];
    // Note: need two state variables for _buf4 !!!
    private int _buf4OffSet = 0;
    private int _buf4Remain = 0;

    public int read(char[] buf, int n) {
        int idx = 0;
        if (_buf4Remain > 0) {
            int copyLen = Math.min(_buf4Remain, n);
            for (int i = 0; i < copyLen; ++i)
                buf[idx++] = _buf4[_buf4OffSet++];
            _buf4Remain -= copyLen;
            n -= copyLen;
        }
        while (n > 0) {
            int len = read4(_buf4);
            _buf4Remain = len;
            _buf4OffSet = 0;
            if (len > n)
                len = n;
            for (int i = 0; i < len && idx < buf.length; ++i)
                buf[idx++] = _buf4[_buf4OffSet++];
            n -= len;
            _buf4Remain -= len;
            if (len <= 0)
                break;
        }
        return idx;
    }
}
