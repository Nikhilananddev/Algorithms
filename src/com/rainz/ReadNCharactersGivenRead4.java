package com.rainz;

/*
 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
 */
public class ReadNCharactersGivenRead4 {
    public static void test(String args[]) {
        char[] input1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
        ReadNCharactersGivenRead4 rn = new ReadNCharactersGivenRead4(input1);
        char[] buf = new char[7];
        int len = rn.read(buf, buf.length);
        System.out.println(len);
        Main.printArray(buf);
    }

    private char[] _data = null;
    private int _offset4 = 0;
    public ReadNCharactersGivenRead4(char[] data) {
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

    public int read(char[] buf, int n) {
        int idx = 0;
        while (n > 0) {
            char[] buf4 = new char[4];
            int len = read4(buf4);
            if (len > n)
                len = n;
            for (int i = 0; i < len && idx < buf.length; ++i)
                buf[idx++] = buf4[i];
            n -= len;
            if (len <= 0)
                break;
        }
        return idx;
    }
}
