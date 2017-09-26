package systems.moellers.wasm;

/**
 * Created on 26.09.17.
 *
 * @author Konstantin Simon Maria Möllers
 */
public interface VarLengthByteBuffer<T> {
    /**
     * Puts a Uint with variable length on this buffer.
     *
     * @param value The value to write
     * @return The written buffer
     */
    T putVarUint(long value);

    long getVarUint();

    /**
     * Puts a Long with variable length on this buffer.
     *
     * @param value The value to write
     * @return The written buffer
     */
    T putVarLong(long value);

    long getVarLong();

    /**
     * Puts a Int with variable length on this buffer.
     *
     * @param value The value to write
     * @return The written buffer
     */
    T putVarInt(int value);

    int getVarInt();

    /**
     * Puts a Short with variable length on this buffer.
     *
     * @param value The value to write
     * @return The written buffer
     */
    T putVarShort(short value);

    short getVarShort();

    /**
     * Puts a Byte with variable length on this buffer.
     *
     * @param value The value to write
     * @return The written buffer
     */
    T putVarByte(byte value);

    byte getVarByte();

    /**
     * Puts a Boolean with variable length on this buffer.
     *
     * @param value The value to write
     * @return The written buffer
     */
    T putVarBoolean(boolean value);

    boolean getVarBoolean();
}
