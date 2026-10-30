const menuToggle = document.getElementById('menuToggle');
const mainNav = document.getElementById('mainNav');

if (menuToggle && mainNav) {
    menuToggle.addEventListener('click', () => mainNav.classList.toggle('open'));
    mainNav.querySelectorAll('a').forEach(link => {
        link.addEventListener('click', () => mainNav.classList.remove('open'));
    });
}

// Puja date: 8 November 2026, midnight in the visitor's local timezone.
const pujaDate = new Date(2026, 10, 8, 0, 0, 0);
function updateCountdown() {
    const now = new Date();
    const distance = pujaDate.getTime() - now.getTime();
    const note = document.getElementById('countdownNote');

    if (distance <= 0) {
        ['days', 'hours', 'minutes', 'seconds'].forEach(id => {
            const element = document.getElementById(id);
            if (element) element.textContent = '00';
        });
        if (note) note.textContent = 'শুভ কালীপুজো! The celebration date has arrived or passed.';
        return;
    }

    const values = {
        days: Math.floor(distance / (1000 * 60 * 60 * 24)),
        hours: Math.floor((distance / (1000 * 60 * 60)) % 24),
        minutes: Math.floor((distance / (1000 * 60)) % 60),
        seconds: Math.floor((distance / 1000) % 60)
    };
    Object.entries(values).forEach(([id, value]) => {
        const element = document.getElementById(id);
        if (element) element.textContent = String(value).padStart(2, '0');
    });
}
updateCountdown();
setInterval(updateCountdown, 1000);


// Reveal or hide the detailed Kali Puja programme when the visitor clicks the button.
const scheduleButton = document.getElementById('showScheduleBtn');
const scheduleSection = document.getElementById('pujaSchedule');

if (scheduleButton && scheduleSection) {
    scheduleButton.addEventListener('click', () => {
        const isHidden = scheduleSection.hidden;
        scheduleSection.hidden = !isHidden;
        scheduleButton.setAttribute('aria-expanded', String(isHidden));
        scheduleButton.innerHTML = isHidden
            ? 'অনুষ্ঠানসূচি বন্ধ করুন <span aria-hidden="true">↑</span>'
            : 'অনুষ্ঠানসূচি দেখুন <span aria-hidden="true">↓</span>';
        if (isHidden) {
            scheduleSection.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
    });
}
