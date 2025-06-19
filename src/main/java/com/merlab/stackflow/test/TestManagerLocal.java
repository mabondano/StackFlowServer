
package com.merlab.stackflow.test;

import jakarta.ejb.Local;

/**
 *
 * @author merly
 */
@Local
public interface TestManagerLocal {
    void runAllTests();
}
