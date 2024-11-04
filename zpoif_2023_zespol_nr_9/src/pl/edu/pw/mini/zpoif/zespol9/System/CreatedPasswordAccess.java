package pl.edu.pw.mini.zpoif.zespol9.System;

import pl.edu.pw.mini.zpoif.zespol9.Exceptions.AlreadySeenThePasswordException;

public interface CreatedPasswordAccess {

    public String getNewPassword() throws AlreadySeenThePasswordException;
}
