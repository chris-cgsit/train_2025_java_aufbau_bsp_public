package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.firma.implementierung.FirmaImplStream;

public class FirmaStreamingTest extends AlltemeinerFirmaTest {

  @Override
  public void createFirmaInstance() {
    firma = new FirmaImplStream("Testfirma Stream Impl ");
  }
}
