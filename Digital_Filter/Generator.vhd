----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 21.11.2021 14:46:48
-- Design Name: 
-- Module Name: Generator - Behavioral
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

entity Generator is
    Port ( clk : in STD_LOGIC;
           enable : in std_logic;
           enable2 : in std_logic;
           Nr : out STD_LOGIC_VECTOR (7 downto 0));
end Generator;

architecture Behavioral of Generator is

begin

process(clk)
variable gen8: std_logic_vector(7 downto 0):=("00000001");
variable aux: std_logic_vector(7 downto 0):=(others=>'0');
begin
   if(rising_edge(clk))then
     if(enable='1' and enable2='1') then
        aux(0):=gen8(7) xor gen8(6);
        gen8(6 downto 1):=gen8(5 downto 0);
        gen8(7):='0';
        gen8(0):=aux(0);
	    Nr<=gen8;
	 end if;
   end if;
end process;

end Behavioral;
