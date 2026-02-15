package com.otaliastudios.cameraview;

import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;

import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.size.Size;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings("unused")
public class PictureResult {

    public static class Stub {
        Stub() {}
        public boolean isSnapshot;
        public Location location;
        public int rotation;
        public Size size;
        public Facing facing;
        public byte[] data;
        public PictureFormat format;
        public Float focusDistanceDiopters;
    }

    private final boolean isSnapshot;
    private final Location location;
    private final int rotation;
    private final Size size;
    private final Facing facing;
    private final byte[] data;
    private final PictureFormat format;
    private final Float focusDistanceDiopters;

    PictureResult(@NonNull Stub builder) {
        isSnapshot = builder.isSnapshot;
        location = builder.location;
        rotation = builder.rotation;
        size = builder.size;
        facing = builder.facing;
        data = builder.data;
        format = builder.format;
        focusDistanceDiopters = builder.focusDistanceDiopters;
    }

    public boolean isSnapshot() { return isSnapshot; }
    @Nullable public Location getLocation() { return location; }
    public int getRotation() { return rotation; }
    @NonNull public Size getSize() { return size; }
    @NonNull public Facing getFacing() { return facing; }
    @NonNull public byte[] getData() { return data; }
    @NonNull public PictureFormat getFormat() { return format; }

    @Nullable
    public Float getFocusDistanceDiopters() { return focusDistanceDiopters; }

    public void toBitmap(int maxWidth, int maxHeight, @NonNull BitmapCallback callback) {
        if (format == PictureFormat.JPEG) {
            CameraUtils.decodeBitmap(getData(), maxWidth, maxHeight, new BitmapFactory.Options(), rotation, callback);
        } else if (format == PictureFormat.DNG && Build.VERSION.SDK_INT >= 24) {
            CameraUtils.decodeBitmap(getData(), maxWidth, maxHeight, new BitmapFactory.Options(), rotation, callback);
        } else {
            throw new UnsupportedOperationException("PictureResult.toBitmap() does not support " + format);
        }
    }

    public void toBitmap(@NonNull BitmapCallback callback) { toBitmap(-1, -1, callback); }

    public void toFile(@NonNull File file, @NonNull FileCallback callback) {
        CameraUtils.writeToFile(getData(), file, callback);
    }
}
