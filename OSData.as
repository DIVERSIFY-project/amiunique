// OSData.as
package {
   
    import flash.display.Sprite;
    import flash.text.Font;
    import flash.text.FontType;
    import flash.text.FontStyle;
    import flash.external.ExternalInterface;
	import flash.system.Capabilities;
   
    public class OSData extends Sprite
    {
        public function OSData()
        {
		 ExternalInterface.addCallback('getFonts', this.getDeviceFonts);
 		 ExternalInterface.addCallback('getOS', this.getOS);
		 ExternalInterface.addCallback('getResolution', this.getResolution);
		 ExternalInterface.addCallback('getLanguage', this.getLanguage);

 		 if  (ExternalInterface.available) {
                	ExternalInterface.call("isConnected");
	         }
        }
       
        public function getDeviceFonts():Array
        {
            var embeddedAndDeviceFonts:Array = Font.enumerateFonts(true);
           
            var deviceFontNames:Array = [];
            for each (var font:Font in embeddedAndDeviceFonts)
            {
                if (font.fontType == FontType.EMBEDDED
                    || font.fontStyle != FontStyle.REGULAR
                    )
                    continue;
                deviceFontNames.push(font.fontName);
            }
           
            return deviceFontNames;
        }
		
		public function getOS():String
		{
			return Capabilities.os;
		}
		
		public function getResolution():Array
		{
			return [Capabilities.screenResolutionX,Capabilities.screenResolutionY];
		}

		public function getLanguage():String
		{
			return Capabilities.language;
		}
    }
}
