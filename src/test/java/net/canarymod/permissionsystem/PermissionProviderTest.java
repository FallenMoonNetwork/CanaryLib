package net.canarymod.permissionsystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class PermissionProviderTest {

    @Test
    public void testQueryPermission() {
        PermissionProvider perms = new PermissionProvider();
        perms.addPermission("foo.bar.allow", true);
        perms.addPermission("foo.bar.deny", false);
        
        perms.addPermission("foo.super.*", true);
        perms.addPermission("foo.super.myass", false);
        
        assertEquals(false, perms.queryPermission("bar.super.someass.funky.nassau"));
//        assertEquals(false, perms.queryPermission("foo.bar.deny"));
    }

}
