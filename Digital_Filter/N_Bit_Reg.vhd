----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 13.11.2021 10:21:04
-- Design Name: 
-- Module Name: N_Bit_Reg - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity N_Bit_Reg is
    Port ( D : in STD_LOGIC_VECTOR (7 downto 0);
           clk : in STD_LOGIC;
           enable : in STD_LOGIC;
           Q : out STD_LOGIC_VECTOR (7 downto 0));
end N_Bit_Reg;

architecture Behavioral of N_Bit_Reg is

begin
    process(Clk,enable)  
      begin   
         if ( rising_edge(clk) and enable='1') then  
                 Q <= D;   
         end if;      
      end process;

end Behavioral;
