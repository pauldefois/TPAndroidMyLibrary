package mylibrary.pauldefois.imta.fr.mylibrary.book;

/**
 * Listener pour la gestion du clic sur un item de la liste de livre
 *
 * @author Paul Defois
 */
public interface OnClickBookItemListener {
    /**
     * Déclenché lors du clic sur un item de la liste de livre
     *
     * @param book le livre dont l'item a été cliqué
     */
    void onClickBook(Book book);
}
