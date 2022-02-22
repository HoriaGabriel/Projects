----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 23.11.2021 20:48:25
-- Design Name: 
-- Module Name: InputReg - Behavioral
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

entity InputReg is
    Port ( clk : in STD_LOGIC;
           inpSel : in STD_LOGIC;
           enable : in STD_LOGIC;
           input : in STD_LOGIC_VECTOR (7 downto 0);
           output : out STD_LOGIC_VECTOR (7 downto 0);
           outenable : out STD_LOGIC);
end InputReg;

architecture Behavioral of InputReg is

signal temp : std_logic_vector(7 downto 0);

begin

process(clk, enable)
begin
    if (rising_edge(clk)) then
        if ( inpSel = '1' and enable = '1' ) then
            temp <= input;
            outenable<='1';
        end if;
    end if;
end process;

output <= temp;

end Behavioral;
