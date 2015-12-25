package io.bumbleb.api;

import java.util.HashMap;

public class BumbleBContentMetadata {
    public BumbleBContentType type;
    public String id;
    public String title;
    public String transcription;
    public String duration;
    public String import_datetime;
    public String release_datetime;
    public String source;
    public BumbleBImageMetadata image;
    public HashMap<String, BumbleBSoundMetadata> sounds;
}
