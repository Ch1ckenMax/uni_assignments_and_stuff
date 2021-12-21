import javax.swing.ImageIcon;

/**
 * Provides ImageIcon objects of cards.
 * 
 * @author Li Hoi Kit
 */
public class CardImages{
    /**
     * Array of card images. left subscript represents suit, and right subscript represents rank.
     */
    public static final ImageIcon[][] cardImages =
    {
        {new ImageIcon("images/cards/ad.gif"),new ImageIcon("images/cards/2d.gif"),new ImageIcon("images/cards/3d.gif"),new ImageIcon("images/cards/4d.gif"),new ImageIcon("images/cards/5d.gif"),new ImageIcon("images/cards/6d.gif"),new ImageIcon("images/cards/7d.gif"),new ImageIcon("images/cards/8d.gif"),new ImageIcon("images/cards/9d.gif"),new ImageIcon("images/cards/td.gif"),new ImageIcon("images/cards/jd.gif"),new ImageIcon("images/cards/qd.gif"),new ImageIcon("images/cards/kd.gif")},
        {new ImageIcon("images/cards/ac.gif"),new ImageIcon("images/cards/2c.gif"),new ImageIcon("images/cards/3c.gif"),new ImageIcon("images/cards/4c.gif"),new ImageIcon("images/cards/5c.gif"),new ImageIcon("images/cards/6c.gif"),new ImageIcon("images/cards/7c.gif"),new ImageIcon("images/cards/8c.gif"),new ImageIcon("images/cards/9c.gif"),new ImageIcon("images/cards/tc.gif"),new ImageIcon("images/cards/jc.gif"),new ImageIcon("images/cards/qc.gif"),new ImageIcon("images/cards/kc.gif")},
        {new ImageIcon("images/cards/ah.gif"),new ImageIcon("images/cards/2h.gif"),new ImageIcon("images/cards/3h.gif"),new ImageIcon("images/cards/4h.gif"),new ImageIcon("images/cards/5h.gif"),new ImageIcon("images/cards/6h.gif"),new ImageIcon("images/cards/7h.gif"),new ImageIcon("images/cards/8h.gif"),new ImageIcon("images/cards/9h.gif"),new ImageIcon("images/cards/th.gif"),new ImageIcon("images/cards/jh.gif"),new ImageIcon("images/cards/qh.gif"),new ImageIcon("images/cards/kh.gif")},
        {new ImageIcon("images/cards/as.gif"),new ImageIcon("images/cards/2s.gif"),new ImageIcon("images/cards/3s.gif"),new ImageIcon("images/cards/4s.gif"),new ImageIcon("images/cards/5s.gif"),new ImageIcon("images/cards/6s.gif"),new ImageIcon("images/cards/7s.gif"),new ImageIcon("images/cards/8s.gif"),new ImageIcon("images/cards/9s.gif"),new ImageIcon("images/cards/ts.gif"),new ImageIcon("images/cards/js.gif"),new ImageIcon("images/cards/qs.gif"),new ImageIcon("images/cards/ks.gif")}
    };

    /**
     * Hidden card image.
     */
    public static final ImageIcon hiddenCard = new ImageIcon("images/cards/b.gif");
}
