----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 13.11.2021 10:19:16
-- Design Name: 
-- Module Name: Top_Level - Behavioral
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
USE IEEE.Std_logic_unsigned.all; 
use IEEE.STD_LOGIC_ARITH.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Top_Level_Filter is
    Port ( clk : in STD_LOGIC;
           inp : in STD_LOGIC_VECTOR (7 downto 0);
           outp : out STD_LOGIC_VECTOR (17 downto 0);
           coeficient1: in STD_LOGIC_VECTOR (7 downto 0);
           coeficient2: in STD_LOGIC_VECTOR (7 downto 0);
           coeficient3: in STD_LOGIC_VECTOR (7 downto 0);
           enable : in STD_LOGIC);
end Top_Level_Filter;

architecture Behavioral of Top_Level_Filter is

component N_bit_Reg  
   port(  
    D : in std_logic_vector(7 downto 0);     
    clk :in std_logic;    
    enable :in std_logic;   
    Q :out std_logic_vector(7 downto 0)    
   );  
 end component;
 
 component BoothMultiplier  
   port(      
      b : in STD_LOGIC_VECTOR (7 downto 0);
      c : in STD_LOGIC_VECTOR (7 downto 0);
      res : out STD_LOGIC_VECTOR (15 downto 0)  
   );  
 end component;
 
 
 component Ripple_Carry_Adder  
   port(  
       a : in STD_LOGIC_VECTOR (15 downto 0);
       b : in STD_LOGIC_VECTOR (15 downto 0);
       cin : in STD_LOGIC;
       s : out STD_LOGIC_VECTOR (15 downto 0);
       cout : out STD_LOGIC    
   );  
 end component;
 
 
 component Ripple_Carry_Adder_2
   port(  
       a : in STD_LOGIC_VECTOR (16 downto 0);
       b : in STD_LOGIC_VECTOR (16 downto 0);
       cin : in STD_LOGIC;
       s : out STD_LOGIC_VECTOR (16 downto 0);
       cout : out STD_LOGIC    
   );  
 end component;



type shift_reg_type is array (0 to 2) of std_logic_vector (7 downto 0);
signal shift_reg: shift_reg_type;
type mult_type is array (0 to 2) of std_logic_vector (15 downto 0);
signal mult : mult_type;  

signal add: std_logic_vector (15 downto 0);
signal caux: std_logic;
signal caux2: std_logic;
signal addaux: std_logic_vector(16 downto 0);
signal mulaux: std_logic_vector(16 downto 0);
signal add2aux: std_logic_vector(16 downto 0);
signal add3aux: std_logic_vector(17 downto 0);

begin
    shift_reg(0)<=inp;
    
    V1: BoothMultiplier
         port map ( b => inp,
                    c => coeficient1,
                    res => mult(0)
                   );
    
    U1: N_bit_Reg
        port map ( Clk => Clk,   
                   enable => enable,  
                   D => shift_reg(0),  
                   Q => shift_reg(1)  
                  );
                  
    V2: BoothMultiplier
         port map ( b => shift_reg(1),
                    c => coeficient2,
                    res => mult(1)
                   );
                   
     X1: Ripple_Carry_Adder
          port map( a => mult(0),
                    b=> mult(1),
                    cin => '0',
                    s => add,
                    cout => caux
                   );
      
    process(caux,addaux,add)
    begin
       if(caux = '1') then
          addaux <= '1' & add;
       else
           addaux <= '0' & add;
       end if;
    end process;
    
    
    U2: N_bit_Reg
        port map ( Clk => Clk,   
                   enable => enable,  
                   D => shift_reg(1),  
                   Q => shift_reg(2)  
                  );
                  
     V3: BoothMultiplier
         port map ( b => shift_reg(2),
                    c => coeficient3,
                    res => mult(2)
                   );
      
      
      mulaux<= '0' & mult(2);
      
      
      X2: Ripple_Carry_Adder_2
          port map( a => addaux,
                    b=> mulaux,
                    cin => '0',
                    s => add2aux,
                    cout => caux2
                   );
       
    process(caux2,addaux,add2aux)
    begin
       if(caux2 = '1') then
          add3aux <= '1' & add2aux;
       else
           add3aux <= '0' & add2aux;
       end if;
    end process;
      
    outp <=add3aux;

end Behavioral;
