package kr.hs.dgsw.de.Protocol;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentProtocol {
    private String storedPath;
    private String originalNamel;

    public AttachmentProtocol(String storedPath, String originalNamel) {
        this.storedPath = storedPath;
        this.originalNamel = originalNamel;
    }
}
