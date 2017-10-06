package Project.Exception;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e03a0e70-bbc2-4d50-9dbe-e0ea44529ec3")
public class MauvaisePiece extends Exception {
    @objid ("66220182-7b24-4c86-809b-2e56df036003")
    public static final String message = new String("Attention la Piece que vous essayez de bouger ne vous appartiens pas !!");
    @objid ("0bab749e-7f93-46ff-8e76-6687cb2d06e4")
    public MauvaisePiece() {
        super(message);
    }
}
