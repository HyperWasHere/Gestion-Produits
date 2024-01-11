package com.AyoubAhmed;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProduitServiceTest {
    private ProduitService produitService;

    @BeforeEach
    public void setUp() {
        produitService = new ProduitService();
    }

    @Test
    public void testAjouterEtReadProduit() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);
        produitService.create(produit);
        assertEquals(produit, produitService.read(1L));
    }
    @Test
    public void testMettreAJourProduit() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);
        produitService.create(produit);
        produit.setPrix(25.0);
        produit.setQuantite(17);
        produit.setNom("ProduitDiff");
        produitService.update(produit);
        assertEquals(25.0, produitService.read(1L).getPrix(), 0.001);
        assertEquals("ProduitDiff", produitService.read(1L).getNom());
        assertEquals(17, produitService.read(1L).getQuantite());
    }
    @Test
    public void testSupprimerProduit() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);
        produitService.create(produit);

        produitService.delete(1L);
        Exception exception = assertThrows(Exception.class, () ->
                produitService.read(1L));
        assertEquals("Produit non trouve", exception.getMessage());
    }
    @Test
    public void testAjouterProduitIdExiste() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);
        produitService.create(produit);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.create(new Produit(1L, "ProduitTest2", 25.0, 6)));
        assertEquals("Un produit avec le meme ID ou nom existe deja", exception.getMessage());
    }
    @Test
    public void testAjouterProduitNomExiste() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);
        produitService.create(produit);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.create(new Produit(2L, "ProduitTest", 25.0, 6)));
        assertEquals("Un produit avec le meme ID ou nom existe deja", exception.getMessage());
    }
    @Test
    public void testAjouterProduitPrixNegatif() {
        Produit produit = new Produit(1L, "ProduitTest", -20.0, 8);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.create(produit));
        assertEquals("Le prix et la quantite du produit doivent etre positifs", exception.getMessage());
    }
    @Test
    public void testAjouterProduitQuantiteNegatif() {
        Produit produit = new Produit(1L, "ProduitTest", 20.0, -8);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.create(produit));
        assertEquals("Le prix et la quantite du produit doivent etre positifs", exception.getMessage());
    }
    @Test
    public void testUpdateProduitPrixNegatif() throws Exception{
        Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);
        produitService.create(produit);
        Produit produit2 = new Produit(1L, "ProduitTest", -20.0, 8);
        Exception exception = assertThrows(Exception.class, () ->
                produitService.update(produit2));
        assertEquals("Le prix et la quantite du produit doivent etre positifs", exception.getMessage());
    }
    @Test
    public void testUpdateProduitQuantiteNegatif() throws Exception{
        Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);
        produitService.create(produit);
        Produit produit2 = new Produit(1L, "ProduitTest", 20.0, -8);
        Exception exception = assertThrows(Exception.class, () ->
                produitService.update(produit2));
        assertEquals("Le prix et la quantite du produit doivent etre positifs", exception.getMessage());
    }
    @Test
    public void testUpdateProduitInexistant() {
        Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.update(produit));
        assertEquals("Produit non trouve", exception.getMessage());
    }   
    @Test
    public void testDeleteProduitInexistant() {
        //Produit produit = new Produit(1L, "ProduitTest", 20.0, 8);
        Exception exception = assertThrows(Exception.class, () ->
                produitService.delete(1L));
        assertEquals("Produit non trouve", exception.getMessage());
    }

}