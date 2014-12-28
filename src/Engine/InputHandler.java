package Engine;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class InputHandler implements KeyListener, MouseListener {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	public InputHandler(Component comp) {
		comp.addKeyListener(this);
		comp.addMouseListener(this);
	}
	
	public class Button {
		private boolean isPressed;
		private double timePressed;
		private int updatesPressed; 
		
		public Button () {
			buttons.add(this);
			isPressed = false;
			timePressed = 0;
			updatesPressed = 0;
		}
		
		public void update(double tpf) {
			if(isPressed) {
				timePressed += tpf;
				updatesPressed++; 
			} else {
				timePressed = 0;
				updatesPressed = 0; 

			}
		}
		
		public void toggle(boolean p) {
			isPressed = p;
		}
		public int updatesPressed() {
			return updatesPressed; 
		}
		
		public boolean isPressed() {
			return isPressed;
		}
		public double timePressed() {
			return timePressed;
		}
	}

	public void update(double tpf) {
		for(Button button: buttons)
			button.update(tpf);
	}
	
	public Button a = new Button();
	public Button b = new Button();
	public Button c = new Button();
	public Button d = new Button();
	public Button e = new Button();
	public Button f = new Button();
	public Button g = new Button();
	public Button h = new Button();
	public Button i = new Button();
	public Button j = new Button();
	public Button k = new Button();
	public Button l = new Button();
	public Button m = new Button();
	public Button n = new Button();
	public Button o = new Button();
	public Button p = new Button();
	public Button q = new Button();
	public Button r = new Button();
	public Button s = new Button();
	public Button t = new Button();
	public Button u = new Button();
	public Button v = new Button();
	public Button w = new Button();
	public Button x = new Button();
	public Button y = new Button();
	public Button z = new Button();
	
	public Button num0 = new Button();
	public Button num1 = new Button();
	public Button num2 = new Button();
	public Button num3 = new Button();
	public Button num4 = new Button();
	public Button num5 = new Button();
	public Button num6 = new Button();
	public Button num7 = new Button();
	public Button num8 = new Button();
	public Button num9 = new Button();
	
	public Button up = new Button();
	public Button down = new Button();
	public Button left = new Button();
	public Button right = new Button();
	
	public Button shift = new Button();
	public Button tab = new Button();
	public Button ctrl = new Button();
	public Button esc = new Button();
	public Button space = new Button(); 
	public Button enter = new Button();
	
	public Button leftMouse = new Button();
	public Button rightMouse = new Button();
	
	
	public void toggle(int keyCode, boolean isPressed) {
		switch(keyCode) {
		case KeyEvent.VK_A:
			a.toggle(isPressed);
			break;
		case KeyEvent.VK_B:
			b.toggle(isPressed);
			break;
		case KeyEvent.VK_C:
			c.toggle(isPressed);
			break;
		case KeyEvent.VK_D:
			d.toggle(isPressed);
			break;
		case KeyEvent.VK_E:
			e.toggle(isPressed);
			break;
		case KeyEvent.VK_F:
			f.toggle(isPressed);
			break;
		case KeyEvent.VK_G:
			g.toggle(isPressed);
			break;
		case KeyEvent.VK_H:
			h.toggle(isPressed);
			break;
		case KeyEvent.VK_I:
			i.toggle(isPressed);
			break;
		case KeyEvent.VK_J:
			j.toggle(isPressed);
			break;
		case KeyEvent.VK_K:
			k.toggle(isPressed);
			break;
		case KeyEvent.VK_L:
			l.toggle(isPressed);
			break;
		case KeyEvent.VK_M:
			m.toggle(isPressed);
			break;
		case KeyEvent.VK_N:
			n.toggle(isPressed);
			break;
		case KeyEvent.VK_O:
			o.toggle(isPressed);
			break;
		case KeyEvent.VK_P:
			p.toggle(isPressed);
			break;
		case KeyEvent.VK_Q:
			q.toggle(isPressed);
			break;
		case KeyEvent.VK_R:
			r.toggle(isPressed);
			break;
		case KeyEvent.VK_S:
			s.toggle(isPressed);
			break;
		case KeyEvent.VK_T:
			t.toggle(isPressed);
			break;
		case KeyEvent.VK_U:
			u.toggle(isPressed);
			break;
		case KeyEvent.VK_V:
			v.toggle(isPressed);
			break;
		case KeyEvent.VK_W:
			w.toggle(isPressed);
			break;
		case KeyEvent.VK_X:
			x.toggle(isPressed);
			break;
		case KeyEvent.VK_Y:
			y.toggle(isPressed);
			break;
		case KeyEvent.VK_Z:
			z.toggle(isPressed);
			break;
			
		case KeyEvent.VK_0:
			num0.toggle(isPressed);
			break;
		case KeyEvent.VK_1:
			num1.toggle(isPressed);
			break;
		case KeyEvent.VK_2:
			num2.toggle(isPressed);
			break;
		case KeyEvent.VK_3:
			num3.toggle(isPressed);
			break;
		case KeyEvent.VK_4:
			num4.toggle(isPressed);
			break;
		case KeyEvent.VK_5:
			num5.toggle(isPressed);
			break;
		case KeyEvent.VK_6:
			num6.toggle(isPressed);
			break;
		case KeyEvent.VK_7:
			num7.toggle(isPressed);
			break;
		case KeyEvent.VK_8:
			num8.toggle(isPressed);
			break;
		case KeyEvent.VK_9:
			num9.toggle(isPressed);
			break;
			
		case KeyEvent.VK_UP:
			up.toggle(isPressed);
			break;
		case KeyEvent.VK_DOWN:
			down.toggle(isPressed);
			break;
		case KeyEvent.VK_LEFT:
			left.toggle(isPressed);
			break;
		case KeyEvent.VK_RIGHT:
			right.toggle(isPressed);
			break;
		case KeyEvent.VK_SHIFT:
			shift.toggle(isPressed);
			break;
		case KeyEvent.VK_TAB:
			tab.toggle(isPressed);
			break;
		case KeyEvent.VK_CONTROL:
			ctrl.toggle(isPressed);
			break;
		case KeyEvent.VK_ESCAPE:
			esc.toggle(isPressed);
			break;
		case KeyEvent.VK_SPACE:
			space.toggle(isPressed);
			break;
		case KeyEvent.VK_ENTER:
			enter.toggle(isPressed);
			break;
		default: 
			System.out.println("ERROR: Bad input 404");
			
		}
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {

	}

	
	public void keyPressed(KeyEvent e) {
		toggle(e.getKeyCode(), true);
	}
	public void keyReleased(KeyEvent e) {
		toggle(e.getKeyCode(), false);
	}

	
	public void keyTyped(KeyEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
