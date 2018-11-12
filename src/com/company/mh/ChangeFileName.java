package com.autodesk.delivery.mastertool;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ToolOptions.class,MasterTool.class})
public class MasterToolTest {
    
    @Test
    public void testMain_valid_args() throws Exception{
        // Prepare
        String[] args = {"arg"} ;
        ToolOptions options = mock(ToolOptions.class);
        BomBuilder bomBuilder = mock(BomBuilder.class);
        
        PowerMockito.mockStatic(ToolOptions.class);
        when(ToolOptions.getInstance()).thenReturn(options);
        when(ToolOptions.getInstance().parse(args)).thenReturn(true); 
        when(ToolOptions.getInstance().getMode()).thenReturn(ToolOptions.Mode.build); 
        when(ToolOptions.getInstance().getManifest()).thenReturn("manifest");
        when(ToolOptions.getInstance().getBom()).thenReturn("bom");
        PowerMockito.whenNew(BomBuilder.class).withNoArguments().thenReturn(bomBuilder);
        
        //call
        MasterTool.main(args); 
        
        //verify
        verify(bomBuilder).build("manifest","bom");
    }
    
    
     @Test
    public void testMain_invalid_args() throws Exception{
        // Prepare
        String[] args = {"arg"} ;
        ToolOptions options = mock(ToolOptions.class);
        BomBuilder bomBuilder = mock(BomBuilder.class);
        
        PowerMockito.mockStatic(ToolOptions.class);
        when(ToolOptions.getInstance()).thenReturn(options);
        when(ToolOptions.getInstance().parse(args)).thenReturn(true); 
        
        //call
        MasterTool.main(args); 
        
        //verify
        verify(bomBuilder,(never())).build("manifest","bom");
    }
    
}
