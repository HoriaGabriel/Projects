----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 13.11.2021 18:30:58
-- Design Name: 
-- Module Name: Full_Implementation - Behavioral
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

entity Full_Implementation is
     Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end Full_Implementation;

architecture Behavioral of Full_Implementation is

component Top_level_Filter is 
port (clk : in STD_LOGIC;
      inp : in STD_LOGIC_VECTOR (7 downto 0);
      outp : out STD_LOGIC_VECTOR (17 downto 0);
      coeficient1: in STD_LOGIC_VECTOR (7 downto 0);
      coeficient2: in STD_LOGIC_VECTOR (7 downto 0);
      coeficient3: in STD_LOGIC_VECTOR (7 downto 0);
      enable : in STD_LOGIC);
end component;

component Generator is 
port (clk : in STD_LOGIC;
      enable: in std_logic;
      enable2: in std_logic;
      Nr : out STD_LOGIC_VECTOR (7 downto 0));
end component;

component button_debounce is
generic (
            COUNTER_SIZE : integer := 10_000 
         );
    port ( clk        : in  std_logic;
           reset      : in  std_logic;
           button_in  : in  std_logic;
           button_out : out std_logic);
 end component;

component InputReg is
port (clk : in STD_LOGIC;
      inpSel: in std_logic;
      enable: in std_logic;
      input : in STD_LOGIC_VECTOR (7 downto 0);
      output : out STD_LOGIC_VECTOR (7 downto 0);
      outenable : out STD_LOGIC
      );
end component;

component SSD is
port (clk : in STD_LOGIC;
      s1 : in STD_LOGIC_VECTOR (17 downto 0);
      cat : out STD_LOGIC_VECTOR (6 downto 0);
      an : out STD_LOGIC_VECTOR (3 downto 0)
      );
end component;


signal Nraux: std_logic_vector(7 downto 0);
signal Outaux: std_logic_vector(17 downto 0);
signal enable: std_logic;
signal enable1: std_logic;
signal enable2: std_logic;
signal enable3: std_logic;
signal enable4: std_logic;
signal enable5: std_logic;
signal coeficient1: std_logic_vector(7 downto 0);
signal coeficient2: std_logic_vector(7 downto 0);
signal coeficient3: std_logic_vector(7 downto 0);
signal outi1 : std_logic_vector (17 downto 0);

begin

C1: button_debounce port map( clk =>clk,
                              reset => '0',
                              button_in => btn(0),
                              button_out => enable
                              );
                              
C11: button_debounce port map( clk =>clk,
                              reset => '0',
                              button_in => btn(1),
                              button_out => enable5
                              );
                           
C2: InputReg port map(
                       clk=> clk,
                       inpSel=> sw(15),
                       enable=>enable,
                       input => sw(7 downto 0),
                       output=>coeficient1,
                       outenable=>enable1                      
                       );
                    
                       
C3: InputReg port map(
                       clk=> clk,
                       inpSel=> sw(14),
                       enable=>enable,
                       input => sw(7 downto 0),
                       output=>coeficient2,
                       outenable=>enable2                      
                       );
                       
                       
C4: InputReg port map(
                       clk=> clk,
                       inpSel=> sw(13),
                       enable=>enable,
                       input => sw(7 downto 0),
                       output=>coeficient3,
                       outenable=>enable3                      
                       );

enable4<= enable1 and enable2 and enable3;
                           
C5: Generator port map (
                         clk => clk,
                         enable=>enable4,
                         enable2=>enable5,
                         Nr => Nraux
                        );

C6: Top_Level_Filter port map(
                               clk => clk,
                               inp => Nraux,
                               outp => Outaux,
                               coeficient1 => coeficient1,
                               coeficient2 => coeficient2,
                               coeficient3 => coeficient3,
                               enable => enable5);
                               
C7: SSD port map(
                   clk=>clk,
                   s1=>Outaux,
                   cat=>cat,
                   an=>an 
                  );

end Behavioral;
