package pl.edu.pw.mini.zpoif.zespol9.System;

import pl.edu.pw.mini.zpoif.zespol9.Catalogue.Catalogue;
import pl.edu.pw.mini.zpoif.zespol9.Exceptions.NoReaderWithThatLoginException;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

public interface SystemAccess {
    Catalogue getCatalogue();

    Reader getReader(String login) throws NoReaderWithThatLoginException;

    void addReader(Reader reader);

    CatalogueAccess getCatalogueAccess();
}
