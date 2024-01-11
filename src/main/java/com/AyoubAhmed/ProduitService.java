package com.AyoubAhmed;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {
    private List<Produit> produits = new ArrayList<>();

    public void create(Produit produit) throws Exception {
        if (produitExiste(produit.getId(), produit.getNom())) {
            throw new Exception("Un produit avec le meme ID ou nom existe deja");
        }
        validerProduit(produit);
        produits.add(produit);
    }

private boolean produitExiste(Long id, String nom) {
        for (Produit p : produits) {
            if (p.getId().equals(id) || p.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

private void validerProduit(Produit produit) throws Exception {
    if (produit.getPrix() < 0 || produit.getQuantite() < 0) {
        throw new Exception("Le prix et la quantite du produit doivent etre positifs");
    }
}

public Produit read(Long id) throws Exception {
 for (Produit produit : produits) {
 if (produit.getId().equals(id)) {
 return produit;
 }
}
throw new Exception("Produit non trouve");
}

}
