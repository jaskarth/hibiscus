package jaskarth.hibiscus.api.client.vbo;

import com.mojang.blaze3d.vertex.VertexBuffer;

public final class Vbo {
    private VertexBuffer buffer;

    public VertexBuffer getBuffer() {
        if (buffer == null) {
            throw new IllegalStateException("VBO not initialized!");
        }

        return buffer;
    }

    public void setBuffer(VertexBuffer buffer) {
        this.buffer = buffer;
    }
}
