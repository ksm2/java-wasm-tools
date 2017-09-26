package systems.moellers.wasm;

import java.nio.ByteBuffer;

/**
 * Created on 26.09.17.
 *
 * @author Konstantin Simon Maria Möllers
 */
interface ByteBufferAccess<T> {
    /**
     * Get a byte of this buffer.
     *
     * @return The read byte
     */
    default byte get() {
        return byteBuffer().get();
    }

    /**
     * Put a byte on this buffer.
     *
     * @param b The byte to write
     * @return The written buffer
     */
    default T put(byte b) {
        byteBuffer().put(b);
        return (T) this;
    }

    /**
     * Flip this buffer.
     *
     * @return The buffer flipped
     */
    default T flip() {
        byteBuffer().flip();
        return (T) this;
    }

    /**
     * Get the current buffer position.
     *
     * @return The buffer position
     */
    default int position() {
        return byteBuffer().position();
    }

    /**
     * Get the current buffer limit.
     *
     * @return The buffer limit
     */
    default int limit() {
        return byteBuffer().limit();
    }

    /**
     * Get the current buffer capacity.
     *
     * @return The buffer capacity
     */
    default int capacity() {
        return byteBuffer().capacity();
    }

    /**
     * Get the underlying byte buffer.
     *
     * @return The byte buffer used
     */
    ByteBuffer byteBuffer();
}
