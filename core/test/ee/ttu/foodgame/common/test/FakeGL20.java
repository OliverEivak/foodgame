package ee.ttu.foodgame.common.test;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.badlogic.gdx.graphics.GL20;

/**
 * Created by TheAks999 on 8/30/2015. Just a fake interface implementation to
 * help with testing
 */
public class FakeGL20 implements GL20 {
    public void glActiveTexture(int texture) {

    }

    public void glBindTexture(int target, int texture) {

    }

    public void glBlendFunc(int sfactor, int dfactor) {

    }

    public void glClear(int mask) {

    }

    public void glClearColor(float red, float green, float blue, float alpha) {

    }

    public void glClearDepthf(float depth) {

    }

    public void glClearStencil(int s) {

    }

    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {

    }

    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
            int imageSize, Buffer data) {

    }

    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
            int format, int imageSize, Buffer data) {

    }

    public void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height,
            int border) {

    }

    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width,
            int height) {

    }

    public void glCullFace(int mode) {

    }

    public void glDeleteTextures(int n, IntBuffer textures) {

    }

    public void glDeleteTexture(int texture) {

    }

    public void glDepthFunc(int func) {

    }

    public void glDepthMask(boolean flag) {

    }

    public void glDepthRangef(float zNear, float zFar) {

    }

    public void glDisable(int cap) {

    }

    public void glDrawArrays(int mode, int first, int count) {

    }

    public void glDrawElements(int mode, int count, int type, Buffer indices) {

    }

    public void glEnable(int cap) {

    }

    public void glFinish() {

    }

    public void glFlush() {

    }

    public void glFrontFace(int mode) {

    }

    public void glGenTextures(int n, IntBuffer textures) {

    }

    public int glGenTexture() {
        return 0;
    }

    public int glGetError() {
        return 0;
    }

    public void glGetIntegerv(int pname, IntBuffer params) {

    }

    public String glGetString(int name) {
        return null;
    }

    public void glHint(int target, int mode) {

    }

    public void glLineWidth(float width) {

    }

    public void glPixelStorei(int pname, int param) {

    }

    public void glPolygonOffset(float factor, float units) {

    }

    public void glReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels) {

    }

    public void glScissor(int x, int y, int width, int height) {

    }

    public void glStencilFunc(int func, int ref, int mask) {

    }

    public void glStencilMask(int mask) {

    }

    public void glStencilOp(int fail, int zfail, int zpass) {

    }

    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
            int type, Buffer pixels) {

    }

    public void glTexParameterf(int target, int pname, float param) {

    }

    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
            int type, Buffer pixels) {

    }

    public void glViewport(int x, int y, int width, int height) {

    }

    public void glAttachShader(int program, int shader) {

    }

    public void glBindAttribLocation(int program, int index, String name) {

    }

    public void glBindBuffer(int target, int buffer) {

    }

    public void glBindFramebuffer(int target, int framebuffer) {

    }

    public void glBindRenderbuffer(int target, int renderbuffer) {

    }

    public void glBlendColor(float red, float green, float blue, float alpha) {

    }

    public void glBlendEquation(int mode) {

    }

    public void glBlendEquationSeparate(int modeRGB, int modeAlpha) {

    }

    public void glBlendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {

    }

    public void glBufferData(int target, int size, Buffer data, int usage) {

    }

    public void glBufferSubData(int target, int offset, int size, Buffer data) {

    }

    public int glCheckFramebufferStatus(int target) {
        return 0;
    }

    public void glCompileShader(int shader) {

    }

    public int glCreateProgram() {
        return 1;
    }

    public int glCreateShader(int type) {
        return 1;
    }

    public void glDeleteBuffer(int buffer) {

    }

    public void glDeleteBuffers(int n, IntBuffer buffers) {

    }

    public void glDeleteFramebuffer(int framebuffer) {

    }

    public void glDeleteFramebuffers(int n, IntBuffer framebuffers) {

    }

    public void glDeleteProgram(int program) {

    }

    public void glDeleteRenderbuffer(int renderbuffer) {

    }

    public void glDeleteRenderbuffers(int n, IntBuffer renderbuffers) {

    }

    public void glDeleteShader(int shader) {

    }

    public void glDetachShader(int program, int shader) {

    }

    public void glDisableVertexAttribArray(int index) {

    }

    public void glDrawElements(int mode, int count, int type, int indices) {

    }

    public void glEnableVertexAttribArray(int index) {

    }

    public void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {

    }

    public void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {

    }

    public int glGenBuffer() {
        return 0;
    }

    public void glGenBuffers(int n, IntBuffer buffers) {

    }

    public void glGenerateMipmap(int target) {

    }

    public int glGenFramebuffer() {
        return 0;
    }

    public void glGenFramebuffers(int n, IntBuffer framebuffers) {

    }

    public int glGenRenderbuffer() {
        return 0;
    }

    public void glGenRenderbuffers(int n, IntBuffer renderbuffers) {

    }

    public String glGetActiveAttrib(int program, int index, IntBuffer size, Buffer type) {
        // Faking out fetchAttributes in ShaderProgram
        return "ActiveAttrib";
    }

    public String glGetActiveUniform(int program, int index, IntBuffer size, Buffer type) {
        // Faking out fetchUniformss in ShaderProgram
        return "ActiveUniform";
    }

    public void glGetAttachedShaders(int program, int maxcount, Buffer count, IntBuffer shaders) {

    }

    public int glGetAttribLocation(int program, String name) {
        return 0;
    }

    public void glGetBooleanv(int pname, Buffer params) {

    }

    public void glGetBufferParameteriv(int target, int pname, IntBuffer params) {

    }

    public void glGetFloatv(int pname, FloatBuffer params) {

    }

    public void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params) {

    }

    public void glGetProgramiv(int program, int pname, IntBuffer params) {
        // faking out linkProgram in ShaderProgram
        params.put(1);
    }

    public String glGetProgramInfoLog(int program) {
        return null;
    }

    public void glGetRenderbufferParameteriv(int target, int pname, IntBuffer params) {

    }

    public void glGetShaderiv(int shader, int pname, IntBuffer params) {
        // faking out the loadShader function in ShaderProgram
        params.put(1);
    }

    public String glGetShaderInfoLog(int shader) {
        return null;
    }

    public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) {

    }

    public void glGetTexParameterfv(int target, int pname, FloatBuffer params) {

    }

    public void glGetTexParameteriv(int target, int pname, IntBuffer params) {

    }

    public void glGetUniformfv(int program, int location, FloatBuffer params) {

    }

    public void glGetUniformiv(int program, int location, IntBuffer params) {

    }

    public int glGetUniformLocation(int program, String name) {
        return 0;
    }

    public void glGetVertexAttribfv(int index, int pname, FloatBuffer params) {

    }

    public void glGetVertexAttribiv(int index, int pname, IntBuffer params) {

    }

    public void glGetVertexAttribPointerv(int index, int pname, Buffer pointer) {

    }

    public boolean glIsBuffer(int buffer) {
        return false;
    }

    public boolean glIsEnabled(int cap) {
        return false;
    }

    public boolean glIsFramebuffer(int framebuffer) {
        return false;
    }

    public boolean glIsProgram(int program) {
        return false;
    }

    public boolean glIsRenderbuffer(int renderbuffer) {
        return false;
    }

    public boolean glIsShader(int shader) {
        return false;
    }

    public boolean glIsTexture(int texture) {
        return false;
    }

    public void glLinkProgram(int program) {

    }

    public void glReleaseShaderCompiler() {

    }

    public void glRenderbufferStorage(int target, int internalformat, int width, int height) {

    }

    public void glSampleCoverage(float value, boolean invert) {

    }

    public void glShaderBinary(int n, IntBuffer shaders, int binaryformat, Buffer binary, int length) {

    }

    public void glShaderSource(int shader, String string) {

    }

    public void glStencilFuncSeparate(int face, int func, int ref, int mask) {

    }

    public void glStencilMaskSeparate(int face, int mask) {

    }

    public void glStencilOpSeparate(int face, int fail, int zfail, int zpass) {

    }

    public void glTexParameterfv(int target, int pname, FloatBuffer params) {

    }

    public void glTexParameteri(int target, int pname, int param) {

    }

    public void glTexParameteriv(int target, int pname, IntBuffer params) {

    }

    public void glUniform1f(int location, float x) {

    }

    public void glUniform1fv(int location, int count, FloatBuffer v) {

    }

    public void glUniform1fv(int location, int count, float[] v, int offset) {

    }

    public void glUniform1i(int location, int x) {

    }

    public void glUniform1iv(int location, int count, IntBuffer v) {

    }

    public void glUniform1iv(int location, int count, int[] v, int offset) {

    }

    public void glUniform2f(int location, float x, float y) {

    }

    public void glUniform2fv(int location, int count, FloatBuffer v) {

    }

    public void glUniform2fv(int location, int count, float[] v, int offset) {

    }

    public void glUniform2i(int location, int x, int y) {

    }

    public void glUniform2iv(int location, int count, IntBuffer v) {

    }

    public void glUniform2iv(int location, int count, int[] v, int offset) {

    }

    public void glUniform3f(int location, float x, float y, float z) {

    }

    public void glUniform3fv(int location, int count, FloatBuffer v) {

    }

    public void glUniform3fv(int location, int count, float[] v, int offset) {

    }

    public void glUniform3i(int location, int x, int y, int z) {

    }

    public void glUniform3iv(int location, int count, IntBuffer v) {

    }

    public void glUniform3iv(int location, int count, int[] v, int offset) {

    }

    public void glUniform4f(int location, float x, float y, float z, float w) {

    }

    public void glUniform4fv(int location, int count, FloatBuffer v) {

    }

    public void glUniform4fv(int location, int count, float[] v, int offset) {

    }

    public void glUniform4i(int location, int x, int y, int z, int w) {

    }

    public void glUniform4iv(int location, int count, IntBuffer v) {

    }

    public void glUniform4iv(int location, int count, int[] v, int offset) {

    }

    public void glUniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer value) {

    }

    public void glUniformMatrix2fv(int location, int count, boolean transpose, float[] value, int offset) {

    }

    public void glUniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer value) {

    }

    public void glUniformMatrix3fv(int location, int count, boolean transpose, float[] value, int offset) {

    }

    public void glUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value) {

    }

    public void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int offset) {

    }

    public void glUseProgram(int program) {

    }

    public void glValidateProgram(int program) {

    }

    public void glVertexAttrib1f(int indx, float x) {

    }

    public void glVertexAttrib1fv(int indx, FloatBuffer values) {

    }

    public void glVertexAttrib2f(int indx, float x, float y) {

    }

    public void glVertexAttrib2fv(int indx, FloatBuffer values) {

    }

    public void glVertexAttrib3f(int indx, float x, float y, float z) {

    }

    public void glVertexAttrib3fv(int indx, FloatBuffer values) {

    }

    public void glVertexAttrib4f(int indx, float x, float y, float z, float w) {

    }

    public void glVertexAttrib4fv(int indx, FloatBuffer values) {

    }

    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer ptr) {

    }

    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, int ptr) {

    }
}