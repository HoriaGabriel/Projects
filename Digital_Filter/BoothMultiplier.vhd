----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10.11.2021 21:41:32
-- Design Name: 
-- Module Name: BoothMultiplier - Behavioral
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
USE ieee.std_logic_signed.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity BoothMultiplier is
    Port ( b : in STD_LOGIC_VECTOR (7 downto 0);
           c : in STD_LOGIC_VECTOR (7 downto 0);
           res : out STD_LOGIC_VECTOR (15 downto 0));
end BoothMultiplier;

architecture Behavioral of BoothMultiplier is


begin


   process(b,c)
			
		variable a, s, p : STD_LOGIC_VECTOR(17 DOWNTO 0);
		variable mn : STD_LOGIC_VECTOR(7 DOWNTO 0);
	
	begin
		
		a := (OTHERS => '0');
		s := (OTHERS => '0');
		p := (OTHERS => '0');
	
			
			a(16 downto 9) := b;
			a(17) := b(7);
			
			mn := (Not b) + 1;
			
			s(16 downto 9) := mn;
			s(17) := Not(b(7));
			
			p(8 downto 1) := c;
			
			for i in 1 to 8 loop
				
				if (p(1 downto 0) = "01") then
					p := p + a;
				elsif (p(1 downto 0) = "10") then
					p := p + s;
				end if;
				
				p(16 downto 0) := p(17 downto 1);
			
			end loop;
		
		res <= p(16 downto 1);
		
	end process;


end Behavioral;
