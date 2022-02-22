----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 13.11.2021 17:12:41
-- Design Name: 
-- Module Name: Ripple_Carry_Adder_2 - Behavioral
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

entity Ripple_Carry_Adder_2 is
    Port ( a : in STD_LOGIC_VECTOR (16 downto 0);
           b : in STD_LOGIC_VECTOR (16 downto 0);
           cin : in STD_LOGIC;
           s : out STD_LOGIC_VECTOR (16 downto 0);
           cout : out STD_LOGIC);
end Ripple_Carry_Adder_2;

architecture Behavioral of Ripple_Carry_Adder_2 is

component full_adder_simple is
Port (a : in STD_LOGIC;
      b : in STD_LOGIC;
      cin : in STD_LOGIC;
      s : out STD_LOGIC;
      cout : out STD_LOGIC);
end component;

signal c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16: STD_LOGIC;

begin

U1: full_adder_simple PORT MAP(
        a=>a(0),
        b=>b(0),
        cin=>cin,
        s=>s(0),
        cout=>c1
     );
   
U2: full_adder_simple PORT MAP(
        a=>a(1),
        b=>b(1),
        cin=>c1,
        s=>s(1),
        cout=>c2
     );

U3: full_adder_simple PORT MAP(
        a=>a(2),
        b=>b(2),
        cin=>c2,
        s=>s(2),
        cout=>c3
     );
     
U4: full_adder_simple PORT MAP(
        a=>a(3),
        b=>b(3),
        cin=>c3,
        s=>s(3),
        cout=>c4
     );
     
U5: full_adder_simple PORT MAP(
        a=>a(4),
        b=>b(4),
        cin=>c4,
        s=>s(4),
        cout=>c5
     );
     
U6: full_adder_simple PORT MAP(
        a=>a(5),
        b=>b(5),
        cin=>c5,
        s=>s(5),
        cout=>c6
     );
     
U7: full_adder_simple PORT MAP(
        a=>a(6),
        b=>b(6),
        cin=>c6,
        s=>s(6),
        cout=>c7
     );
     
U8: full_adder_simple PORT MAP(
        a=>a(7),
        b=>b(7),
        cin=>c7,
        s=>s(7),
        cout=>c8
     );
     
 U9: full_adder_simple PORT MAP(
        a=>a(8),
        b=>b(8),
        cin=>c8,
        s=>s(8),
        cout=>c9
     );
  
 U10: full_adder_simple PORT MAP(
        a=>a(9),
        b=>b(9),
        cin=>c9,
        s=>s(9),
        cout=>c10
     );
     
 U11: full_adder_simple PORT MAP(
        a=>a(10),
        b=>b(10),
        cin=>c10,
        s=>s(10),
        cout=>c11
     );
     
  U12: full_adder_simple PORT MAP(
        a=>a(11),
        b=>b(11),
        cin=>c11,
        s=>s(11),
        cout=>c12
     );
     
   U13: full_adder_simple PORT MAP(
        a=>a(12),
        b=>b(12),
        cin=>c12,
        s=>s(12),
        cout=>c13
     );
     
    U14: full_adder_simple PORT MAP(
        a=>a(13),
        b=>b(13),
        cin=>c13,
        s=>s(13),
        cout=>c14
     );
     
     
    U15: full_adder_simple PORT MAP(
        a=>a(14),
        b=>b(14),
        cin=>c14,
        s=>s(14),
        cout=>c15
     );
     
     U16: full_adder_simple PORT MAP(
        a=>a(15),
        b=>b(15),
        cin=>c15,
        s=>s(15),
        cout=>c16
     );
     
     U17: full_adder_simple PORT MAP(
        a=>a(16),
        b=>b(16),
        cin=>c16,
        s=>s(16),
        cout=>cout
     );


end Behavioral;
