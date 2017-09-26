package systems.moellers.wasm.test;

import org.junit.Before;
import org.junit.Test;
import systems.moellers.wasm.LEB128;
import systems.moellers.wasm.LEB128ByteBuffer;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * Created on 26.09.17.
 *
 * @author Konstantin Simon Maria Möllers
 */
public class LEB128ByteBufferTest {
    private LEB128ByteBuffer buffer;

    @Before
    public void setUp() throws Exception {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(5000);
        buffer = new LEB128ByteBuffer(byteBuffer);
        assertEquals(0, buffer.position());
    }

    @Test
    public void testVarUint() throws Exception {
        // Write
        buffer.putVarUint(0);
        assertEquals(1, buffer.position());

        buffer.putVarUint(1);
        assertEquals(2, buffer.position());

        buffer.putVarUint(2);
        assertEquals(3, buffer.position());

        buffer.putVarUint(-1);
        assertEquals(13, buffer.position());

        buffer.putVarUint(LEB128.MAX_VAR_UINT63);
        assertEquals(22, buffer.position());

        // Flip the buffer
        buffer.flip();
        assertEquals(0, buffer.position());

        // Read
        assertEquals(0L, buffer.getVarUint());
        assertEquals(1, buffer.position());

        assertEquals(1L, buffer.getVarUint());
        assertEquals(2, buffer.position());

        assertEquals(2L, buffer.getVarUint());
        assertEquals(3, buffer.position());

        assertEquals(-1L, buffer.getVarUint());
        assertEquals(13, buffer.position());

        assertEquals(LEB128.MAX_VAR_UINT63, buffer.getVarUint());
        assertEquals(22, buffer.position());
    }

    @Test
    public void testVarLong() throws Exception {
        // Write
        buffer.putVarLong(0);
        assertEquals(1, buffer.position());

        buffer.putVarLong(1);
        assertEquals(2, buffer.position());

        buffer.putVarLong(-1);
        assertEquals(3, buffer.position());

        buffer.putVarLong(LEB128.MAX_VAR_INT63);
        assertEquals(12, buffer.position());

        buffer.putVarLong(LEB128.MIN_VAR_INT63);
        assertEquals(21, buffer.position());

        // Flip the buffer
        buffer.flip();
        assertEquals(0, buffer.position());

        // Read
        assertEquals(0L, buffer.getVarLong());
        assertEquals(1, buffer.position());

        assertEquals(1L, buffer.getVarLong());
        assertEquals(2, buffer.position());

        assertEquals(-1L, buffer.getVarLong());
        assertEquals(3, buffer.position());

        assertEquals(LEB128.MAX_VAR_INT63, buffer.getVarLong());
        assertEquals(12, buffer.position());

        assertEquals(LEB128.MIN_VAR_INT63, buffer.getVarLong());
        assertEquals(21, buffer.position());
    }

    @Test
    public void testVarInt() throws Exception {
        // Write
        buffer.putVarInt(0);
        assertEquals(1, buffer.position());

        buffer.putVarInt(1);
        assertEquals(2, buffer.position());

        buffer.putVarInt(-1);
        assertEquals(3, buffer.position());

        buffer.putVarInt(LEB128.MAX_VAR_INT32);
        assertEquals(8, buffer.position());

        buffer.putVarInt(LEB128.MIN_VAR_INT32);
        assertEquals(13, buffer.position());

        // Flip the buffer
        buffer.flip();
        assertEquals(0, buffer.position());

        // Read
        assertEquals(0L, buffer.getVarInt());
        assertEquals(1, buffer.position());

        assertEquals(1L, buffer.getVarInt());
        assertEquals(2, buffer.position());

        assertEquals(-1L, buffer.getVarInt());
        assertEquals(3, buffer.position());

        assertEquals(LEB128.MAX_VAR_INT32, buffer.getVarInt());
        assertEquals(8, buffer.position());

        assertEquals(LEB128.MIN_VAR_INT32, buffer.getVarInt());
        assertEquals(13, buffer.position());
    }

    @Test
    public void testVarShort() throws Exception {
        // Write
        buffer.putVarShort((byte) 0);
        assertEquals(1, buffer.position());

        buffer.putVarShort((byte) 1);
        assertEquals(2, buffer.position());

        buffer.putVarShort((byte) -1);
        assertEquals(3, buffer.position());

        buffer.putVarShort(LEB128.MAX_VAR_INT14);
        assertEquals(5, buffer.position());

        buffer.putVarShort(LEB128.MIN_VAR_INT14);
        assertEquals(7, buffer.position());

        // Flip the buffer
        buffer.flip();
        assertEquals(0, buffer.position());

        // Read
        assertEquals(0L, buffer.getVarShort());
        assertEquals(1, buffer.position());

        assertEquals(1L, buffer.getVarShort());
        assertEquals(2, buffer.position());

        assertEquals(-1L, buffer.getVarShort());
        assertEquals(3, buffer.position());

        assertEquals(LEB128.MAX_VAR_INT14, buffer.getVarShort());
        assertEquals(5, buffer.position());

        assertEquals(LEB128.MIN_VAR_INT14, buffer.getVarShort());
        assertEquals(7, buffer.position());
    }

    @Test
    public void testVarByte() throws Exception {
        // Write
        buffer.putVarByte((byte) 0);
        assertEquals(1, buffer.position());

        buffer.putVarByte((byte) 1);
        assertEquals(2, buffer.position());

        buffer.putVarByte((byte) -1);
        assertEquals(3, buffer.position());

        buffer.putVarByte(LEB128.MAX_VAR_INT7);
        assertEquals(4, buffer.position());

        buffer.putVarByte(LEB128.MIN_VAR_INT7);
        assertEquals(5, buffer.position());

        // Flip the buffer
        buffer.flip();
        assertEquals(0, buffer.position());

        // Read
        assertEquals(0L, buffer.getVarByte());
        assertEquals(1, buffer.position());

        assertEquals(1L, buffer.getVarByte());
        assertEquals(2, buffer.position());

        assertEquals(-1L, buffer.getVarByte());
        assertEquals(3, buffer.position());

        assertEquals(LEB128.MAX_VAR_INT7, buffer.getVarByte());
        assertEquals(4, buffer.position());

        assertEquals(LEB128.MIN_VAR_INT7, buffer.getVarByte());
        assertEquals(5, buffer.position());
    }

    @Test
    public void testVarBoolean() throws Exception {
        // Write
        buffer.putVarBoolean(true);
        assertEquals(1, buffer.position());

        buffer.putVarBoolean(false);
        assertEquals(2, buffer.position());

        // Flip the buffer
        buffer.flip();
        assertEquals(0, buffer.position());

        // Read
        assertTrue(buffer.getVarBoolean());
        assertEquals(1, buffer.position());

        assertFalse(buffer.getVarBoolean());
        assertEquals(2, buffer.position());
    }
}
