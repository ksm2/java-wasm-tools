package systems.moellers.wasm;

import java.nio.ByteBuffer;

/**
 * Created on 24.09.17.
 *
 * @author Konstantin Simon Maria Möllers
 */
public class LEB128ByteBuffer implements ByteBufferAccess<LEB128ByteBuffer>, VarLengthByteBuffer<LEB128ByteBuffer> {

    private final ByteBuffer buffer;

    public LEB128ByteBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public ByteBuffer byteBuffer() {
        return buffer;
    }

    @Override
    public LEB128ByteBuffer putVarUint(long value) {
        byte currentByte = (byte) (value & 0x7f);
        value >>>= 7;

        if (value == 0) {
            return put(currentByte);
        }

        // Set the highest order bit
        currentByte |= 0x80;
        put(currentByte);
        return putVarUint(value);
    }

    @Override
    public long getVarUint() {
        byte currentByte = get();
        boolean hasNext = (currentByte & 0x80) != 0;
        if (hasNext) {
            currentByte &= 0x7f;
            return (getVarUint() << 7) | currentByte;
        }

        return currentByte;
    }

    @Override
    public LEB128ByteBuffer putVarLong(long value) {
        byte currentByte = (byte) (value & 0x7f);
        value >>= 7;

        if ((value == 0 && (currentByte & 0x40) == 0) || (value == -1 && (currentByte & 0x40) != 0)) {
            return put(currentByte);
        }

        // Set the highest order bit
        currentByte |= 0x80;
        put(currentByte);
        return putVarLong(value);
    }

    @Override
    public long getVarLong() {
        byte currentByte = get();
        boolean hasNext = (currentByte & 0x80) != 0;
        if (hasNext) {
            currentByte &= 0x7f;
            return (getVarLong() << 7) | currentByte;
        }

        boolean isSigned = (currentByte & 0x40) != 0;
        return isSigned ? 0xffffffffffffff80L | currentByte : currentByte;
    }

    @Override
    public LEB128ByteBuffer putVarInt(int value) {
        byte currentByte = (byte) (value & 0x7f);
        value >>= 7;

        if ((value == 0 && (currentByte & 0x40) == 0) || (value == -1 && (currentByte & 0x40) != 0)) {
            return put(currentByte);
        }

        // Set the highest order bit
        currentByte |= 0x80;
        put(currentByte);
        return putVarInt(value);
    }

    @Override
    public int getVarInt() {
        byte currentByte = get();
        boolean hasNext = (currentByte & 0x80) != 0;
        if (hasNext) {
            currentByte &= 0x7f;
            return (getVarInt() << 7) | currentByte;
        }

        boolean isSigned = (currentByte & 0x40) != 0;
        return isSigned ? 0xffffff80 | currentByte : currentByte;
    }

    @Override
    public LEB128ByteBuffer putVarShort(short value) {
        byte currentByte = (byte) (value & 0x7f);
        value >>= 7;

        if ((value == 0 && (currentByte & 0x40) == 0) || (value == -1 && (currentByte & 0x40) != 0)) {
            return put(currentByte);
        }

        // Set the highest order bit
        currentByte |= 0x80;
        put(currentByte);
        return putVarShort(value);
    }

    @Override
    public short getVarShort() {
        byte currentByte = get();
        boolean hasNext = (currentByte & 0x80) != 0;
        if (hasNext) {
            currentByte &= 0x7f;
            return (short) ((getVarShort() << 7) | currentByte);
        }

        short result = currentByte;
        if ((currentByte & 0x40) != 0) {
            result |= 0xffffff80;
        }
        return result;
    }

    @Override
    public LEB128ByteBuffer putVarByte(byte value) {
        byte currentByte = (byte) (value & 0x7f);
        value >>= 7;

        if ((value == 0 && (currentByte & 0x40) == 0) || (value == -1 && (currentByte & 0x40) != 0)) {
            return put(currentByte);
        }

        // Set the highest order bit
        currentByte |= 0x80;
        put(currentByte);
        return putVarByte(value);
    }

    @Override
    public byte getVarByte() {
        byte currentByte = get();
        boolean hasNext = (currentByte & 0x80) != 0;
        if (hasNext) {
            throw new RuntimeException("Not a byte");
        }

        // Is signed? Set highest bit
        if ((currentByte & 0x40) != 0) {
            currentByte |= 0x80;
        }
        return currentByte;
    }

    @Override
    public LEB128ByteBuffer putVarBoolean(boolean value) {
        return put((byte) (value ? 0x01 : 0x00));
    }

    @Override
    public boolean getVarBoolean() {
        byte currentByte = get();
        if (currentByte == 0x00) {
            return false;
        }
        if (currentByte == 0x01) {
            return true;
        }

        throw new RuntimeException("Not a boolean");
    }
}
