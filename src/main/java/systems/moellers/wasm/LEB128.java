package systems.moellers.wasm;

/**
 * Created on 26.09.17.
 *
 * @author Konstantin Simon Maria Möllers
 */
public class LEB128 {
    public static final long MAX_VAR_UINT63 = 0x7fffffffffffffffL;
    public static final long MAX_VAR_INT63 = 0x3fffffffffffffffL;
    public static final long MIN_VAR_INT63 = 0xc000000000000000L;

    public static final long MAX_VAR_UINT32 = 4294967295L;
    public static final int MAX_VAR_INT32 = Integer.MAX_VALUE;
    public static final int MIN_VAR_INT32 = Integer.MIN_VALUE;

    public static final int MAX_VAR_UINT28 = 268435455;
    public static final int MAX_VAR_INT28 = 134217727;
    public static final int MIN_VAR_INT28 = -134217728;

    public static final int MAX_VAR_UINT21 = 2097151;
    public static final int MAX_VAR_INT21 = 1048575;
    public static final int MIN_VAR_INT21 = -1048576;

    public static final int MAX_VAR_UINT14 = 16383;
    public static final short MAX_VAR_INT14 = 8191;
    public static final short MIN_VAR_INT14 = -8192;

    public static final byte MAX_VAR_UINT7 = 127;
    public static final byte MAX_VAR_INT7 = 63;
    public static final byte MIN_VAR_INT7 = -64;
}
